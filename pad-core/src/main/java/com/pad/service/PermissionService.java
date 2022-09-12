package com.pad.service;

import com.pad.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pad.vo.PermissionQuery;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
public interface PermissionService extends IService<Permission> {
    //获取菜单列表
    List<Permission> getList(PermissionQuery permission);
    //判断是否有子菜单
    boolean hasChildren(String id);
    //判断是否已被关联
    boolean hasRole(String id);
    //逻辑删除菜单
    void removeMenu(String id);
    //获取树形选择菜单 不显示被禁用 被删除的
    List<Permission> getListTree();
    //父菜单禁用 子菜单也变禁用
    void updateSubmenu(Permission permission);
}
