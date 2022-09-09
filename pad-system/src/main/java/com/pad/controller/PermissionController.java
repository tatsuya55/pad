package com.pad.controller;


import com.pad.entity.Permission;
import com.pad.response.R;
import com.pad.service.PermissionService;
import com.pad.vo.PermissionQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Api(tags = "权限管理")
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PreAuthorize("@me.hasAuthority('system:menu:list')")
    @ApiOperation("获取菜单列表")
    @PostMapping("/list")
    public R getList(
            @ApiParam(name = "permission",value = "查询条件",required = false)
            @RequestBody(required = false) PermissionQuery permission){
        //返回树形结构
        List<Permission> perTreeList = permissionService.getList(permission);
        return R.ok().data("perTreeList",perTreeList);
    }

    @PreAuthorize("@me.hasAuthority('system:menu:add')")
    @ApiOperation("添加菜单")
    @PostMapping("/add")
    public R addMenu(
            @ApiParam(name = "permission",value = "添加的菜单",required = true)
            @RequestBody Permission permission
    ){
        permissionService.save(permission);
        return R.ok().message("添加成功");
    }

}

