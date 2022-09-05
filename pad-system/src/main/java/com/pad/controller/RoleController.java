package com.pad.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.Role;
import com.pad.response.R;
import com.pad.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

}

