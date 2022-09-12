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

    @ApiOperation("获取树形选择菜单")
    @PostMapping("/listTree")
    public R getListTree(){
        //获取树形选择菜单 不显示被禁用 被删除的
        List<Permission> perTreeList = permissionService.getListTree();
        return R.ok().data("perTreeList",perTreeList);
    }

    @PreAuthorize("@me.hasAuthority('system:menu:list')")
    @ApiOperation("获取菜单列表")
    @PostMapping("/list")
    public R getList(
            @ApiParam(name = "permission",value = "查询条件",required = false)
            @RequestBody(required = false) PermissionQuery permission){
        //返回树形结构列表
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

    @PreAuthorize("@me.hasAuthority('system:menu:remove')")
    @ApiOperation("删除菜单")
    @DeleteMapping("/{id}")
    public R removeMenu(
            @ApiParam(name = "id",value = "菜单编号",required = true)
            @PathVariable String id
    ){
        //有子菜单 无法删除
        if (permissionService.hasChildren(id)){
            return R.error().message("存在子菜单,不允许删除");
        }
        //已被关联 无法删除
        if (permissionService.hasRole(id)){
            return R.error().message("已被关联,不允许删除");
        }
        //逻辑删除
        permissionService.removeMenu(id);
        return R.ok().message("删除成功");
    }

    @PreAuthorize("@me.hasAuthority('system:menu:query')")
    @ApiOperation("根据菜单id获取菜单")
    @GetMapping("/{id}")
    public R getMenuById(
            @ApiParam(name = "id",value = "菜单id",required = true)
            @PathVariable String id
    ){
        Permission permission = permissionService.getById(id);
        return R.ok().data("permission",permission);
    }

    @PreAuthorize("@me.hasAuthority('system:menu:edit')")
    @ApiOperation("修改菜单")
    @PostMapping("/edit")
    public R editMenu(
            @ApiParam(name = "permission",value = "要修改的菜单",required = true)
            @RequestBody Permission permission
    ){
        permissionService.updateById(permission);
        //父菜单禁用 子菜单也变禁用
        permissionService.updateSubmenu(permission);
        return R.ok().message("修改成功");
    }
}

