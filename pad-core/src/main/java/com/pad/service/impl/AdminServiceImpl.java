package com.pad.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.Admin;
import com.pad.entity.AdminRole;
import com.pad.mapper.AdminMapper;
import com.pad.mapper.AdminRoleMapper;
import com.pad.response.R;
import com.pad.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 管理员信息表 服务实现类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Resource
    private AdminRoleMapper adminRoleMapper;

    //用户列表分页显示
    @Override
    public void pageQuery(Page<Admin> adminPage, Admin admin) {
        //构造条件
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        //显示未删除的用户
        wrapper.eq(Admin::getIsDeleted,1);
        //判断条件是否为空
        if (ObjectUtils.isEmpty(admin)){
            //条件为空 直接分页查询
            baseMapper.selectPage(adminPage,wrapper);
            return;
        }
        //判断单个条件是否为空
        //用户名
        String name = admin.getName();
        //手机号
        String phone = admin.getPhone();
        //状态
        Integer isDeleted = admin.getIsDeleted();
        if (StringUtils.hasText(name)){
            wrapper.like(Admin::getName,name);
        }
        if (StringUtils.hasText(phone)){
            wrapper.like(Admin::getPhone,phone);
        }
        if (isDeleted !=null){
            wrapper.eq(Admin::getIsDeleted,isDeleted);
        }
        //查询
        baseMapper.selectPage(adminPage,wrapper);
    }

    //根据用户id查询对应角色列表 返回角色id
    @Override
    public List<Integer> getRoleIds(String userId) {
        return baseMapper.selectRoleIdsByUserId(userId);
    }

    //逻辑删除用户
    @Override
    public void removeAdmin(List<String> ids) {
        baseMapper.deleteAdminByIds(ids);
    }
}
