package com.pad.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    //根据用户id查询对应角色列表 返回角色id
    List<Integer> getRoleIds(String userId);
    //逻辑删除用户
    void removeAdmin(List<String> ids);
    //导出后台用户
    List<Admin> exportAdminExcel(List<String> ids);
}
