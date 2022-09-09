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
}
