package com.pad.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 管理员信息表 服务类
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
public interface AdminService extends IService<Admin> {
    //用户列表分页显示
    void pageQuery(Page<Admin> adminPage, Admin admin);
}
