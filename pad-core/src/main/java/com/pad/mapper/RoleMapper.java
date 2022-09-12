package com.pad.mapper;

import com.pad.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
public interface RoleMapper extends BaseMapper<Role> {
    //查询角色对应权限id列表
    List<Integer> selectMenuIdsByRoleId(String id);
    //逻辑删除角色
    void deleteRoleByIds(List<String> asList);
}
