package com.pad.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pad.entity.Permission;
import com.pad.mapper.AdminMapper;
import com.pad.mapper.PermissionMapper;
import com.pad.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
/*import com.pad.utils.SecurityUtils;*/
import com.pad.utils.SecurityUtils;
import com.pad.vo.PermissionQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private AdminMapper adminMapper;

    //获取菜单列表
    @Override
    public List<Permission> getList(PermissionQuery permission) {
        //获取当前用户id
        String userId = SecurityUtils.getUserId();
        //权限列表
        List<Permission> permissionList = null;
        //构造条件
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        //如果条件不为空
        if (!ObjectUtils.isEmpty(permission)){
            String name = permission.getName();
            String value = permission.getPermissionValue();
            Integer status = permission.getStatus();
            if (StringUtils.hasText(name)){
                wrapper.like(Permission::getName,name);
            }
            if (StringUtils.hasText(value)){
                wrapper.like(Permission::getPermissionValue,value);
            }
            if (status != null){
                wrapper.eq(Permission::getStatus,status);
            }
        }
        //管理员显示所有菜单 查询单表
        if ("1".equals(userId)){
            permissionList = baseMapper.selectList(wrapper);
        }else {
            //其他显示自己对应的权限菜单 多表联查
            permission.setUserId(userId);
            permissionList = adminMapper.selectPermissionQuery(permission);
        }
        List<Permission> tree = getTree(permissionList);
        return tree;
    }

    //返回树形结构
    public List<Permission> getTree(List<Permission> permissionList){
        List<Permission> tree = new ArrayList<>();
        //一级菜单
        /*LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Permission::getPid,0);
        tree = baseMapper.selectList(wrapper);*/
        for (Permission permission : permissionList) {
            if (permission.getPid() == 0){
                tree.add(permission);
            }
        }
        if (tree.size()==0){
            for (Permission permission : permissionList) {
                if (permission.getPid() == 1){
                    tree.add(permission);
                }
            }
        }
        if (tree.size()==0){
            for (Permission permission : permissionList) {
                    tree.add(permission);
            }
        }
        //子菜单
        for (Permission permission : tree) {
            List<Permission> child = getChild(permission.getId(),permissionList);
            permission.setChildren(child);
        }
        return tree;
    }

    //获得子菜单
    private List<Permission> getChild(Integer id, List<Permission> permissionList) {
        List<Permission> childList = new ArrayList<>();
        for (Permission permission : permissionList) {
            if (permission.getPid().equals(id)){
                childList.add(permission);
            }
        }
        for (Permission permission : childList) {
            //递归调用
            List<Permission> child = getChild(permission.getId(),permissionList);
            permission.setChildren(child);
        }
        if(childList.size()==0){
            //子菜单的长度为0,返回null
            return  null;
        }
        return childList;
    }
}
