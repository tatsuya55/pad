package com.pad.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
public interface RoleService extends IService<Role> {
    //角色列表分页显示
    void queryPage(Page<Role> rolePage, Role role);
}
