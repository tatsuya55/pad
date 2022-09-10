package com.pad.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.Admin;
import com.pad.entity.AdminRole;
import com.pad.entity.Role;
import com.pad.entity.RolePermission;
import com.pad.response.R;
import com.pad.service.AdminRoleService;
import com.pad.service.RolePermissionService;
import com.pad.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminRoleService adminRoleService;

    @ApiParam
    private RolePermissionService rolePermissionService;

    @PreAuthorize("@me.hasAuthority('system:role:list')")
    @ApiOperation("角色列表分页显示")
    @PostMapping("/list/{current}/{limit}")
    public R roleListPage(
            @ApiParam(name = "current",value = "当前页",required = true)
            @PathVariable long current,
            @ApiParam(name = "limit",value = "每页记录数",required = true)
            @PathVariable long limit,
            @ApiParam(name = "role",value = "查询条件",required = false)
            @RequestBody(required = false) Role role
    ){
        //创建page对象
        Page<Role> rolePage = new Page<>(current,limit);
        //查询条件封装在service中
        roleService.queryPage(rolePage,role);
        //获取分页后的列表和总记录数
        List<Role> roleList = rolePage.getRecords();
        long total = rolePage.getTotal();
        return R.ok().data("total",total).data("roleList",roleList);
    }

    @PreAuthorize("@me.hasAuthority('system:role:query')")
    @ApiOperation("获取角色选择列表")
    @GetMapping("/options")
    public R getRoleOptions(){
        List<Role> roles = roleService.list(
                new LambdaQueryWrapper<Role>().eq(Role::getIsDeleted, 1));
        return R.ok().data("roleOptions",roles);
    }

    @PreAuthorize("@me.hasAuthority('system:role:edit')")
    @ApiOperation("修改角色状态")
    @PutMapping("/changeStatus")
    public R changeStatus(
            @ApiParam(name = "role",value = "要修改的角色信息",required = true)
            @RequestBody Role role
    ){
        roleService.updateById(role);
        return R.ok().message("更改状态成功");
    }

    @PreAuthorize("@me.hasAuthority('system:role:query')")
    @ApiOperation("根据id查询角色")
    @GetMapping("/{id}")
    public R getRoleById(
            @ApiParam(name = "id",value = "角色id",required = true)
            @PathVariable String id
    ){
        Role role = roleService.getById(id);
        return R.ok().data("role",role);
    }

    @ApiOperation("查询角色对应权限id列表")
    @GetMapping("/getMenuIds/{id}")
    public R getMenuIdsByRoleId(
            @ApiParam(name = "id",value = "角色id",required = true)
            @PathVariable String id
    ){
        List<Integer> menuIds = roleService.getMenuIdsByRoleId(id);
        return R.ok().data("menuIds",menuIds);
    }


    @PreAuthorize("@me.hasAuthority('system:role:remove')")
    @ApiOperation("根据id删除角色")
    @DeleteMapping("/{id}")
    public R removeAdmin(
            @ApiParam(name = "id",value = "要删除的角色id",required = true)
            @PathVariable String[] id
    ){
        List<String> asList = Arrays.asList(id);
        //已被关联 无法删除
        int count = adminRoleService.count(
                new LambdaQueryWrapper<AdminRole>()
                .in(AdminRole::getRoleId, asList));
        if (count > 0){
            return R.error().message("当前角色已被分配，无法删除，请先删除分配");
        }
        rolePermissionService.remove(
                new LambdaQueryWrapper<RolePermission>()
                .in(RolePermission::getRoleId,asList));
        //逻辑删除角色
        roleService.removeRole(asList);
        return R.ok();
    }

}

