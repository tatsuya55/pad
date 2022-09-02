package com.pad.service.impl;

import com.pad.entity.Permission;
import com.pad.mapper.PermissionMapper;
import com.pad.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
