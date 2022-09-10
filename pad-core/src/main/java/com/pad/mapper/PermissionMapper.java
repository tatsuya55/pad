package com.pad.mapper;

import com.pad.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    //判断是否有子菜单
    Integer hasChildrenById(String id);
    //判断是否已被关联
    Integer hasRoleById(String id);
    //逻辑删除菜单
    void deleteMenuById(String id);
}
