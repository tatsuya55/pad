package com.pad.mapper;

import com.pad.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pad.entity.Permission;
import com.pad.entity.Role;

import java.util.List;

/**
 * <p>
 * 管理员信息表 Mapper 接口
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
public interface AdminMapper extends BaseMapper<Admin> {
    //根据用户id查询对应角色列表 返回角色名称
    List<String> selectRoleNameByUserId(String userId);

    //根据用户id查询对应角色列表 返回角色id
    List<Integer> selectRoleIdsByUserId(String userId);

    //根据用户id查询对应权限列表
    List<String> selectPermissionByUserId(String userId);

    //逻辑删除用户
    void deleteAdminByIds(List<String> ids);
}
