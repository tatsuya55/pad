package com.pad.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.Admin;
import com.pad.entity.AdminRole;
import com.pad.response.R;
import com.pad.service.AdminRoleService;
import com.pad.service.AdminService;
import com.pad.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 管理员信息表 前端控制器
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */

@Api(tags = "后台用户管理")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRoleService adminRoleService;

    /*@ApiOperation("测试接口")
    @GetMapping("/test")
    //SpEL表达式语法 @me是一个自己配置的spring容器起的别名，能够正确的找到这个容器类
    @PreAuthorize("@me.hasAuthority('company')")
    //@PreAuthorize("hasAuthority('company11')")
    //拥有test权限的才能访问
    public String test(){
        return "aaa";
    }*/

    @PreAuthorize("@me.hasAuthority('system:user:list')")
    @ApiOperation("用户列表分页显示")
    @PostMapping("/list/{current}/{limit}")
    public R adminListPage(
        @ApiParam(name = "current",value = "当前页",required = true)
        @PathVariable long current,
        @ApiParam(name = "limit",value = "每页记录数",required = true)
        @PathVariable long limit,
        @ApiParam(name = "admin",value = "查询条件",required = false)
        @RequestBody(required = false) Admin admin
    ){
        //创建page对象
        Page<Admin> adminPage = new Page<Admin>(current, limit);
        //查询条件封装在service中
        adminService.pageQuery(adminPage,admin);
        //获取分页后的列表和总记录数
        List<Admin> adminList = adminPage.getRecords();
        long total = adminPage.getTotal();
        return R.ok().data("total",total).data("adminList",adminList);
    }

    @PreAuthorize("@me.hasAuthority('system:user:add')")
    @ApiOperation("添加后台用户")
    @PostMapping("/add")
    public R addAdmin(
        @ApiParam(name = "admin",value = "添加的用户",required = true)
        @RequestBody Admin admin
    ){
        //密码加密
        String encode = SecurityUtils.encryptPassword(admin.getPassword());
        admin.setPassword(encode);
        //添加用户
        adminService.save(admin);
        //添加用户对应角色
        this.insertUserRole(admin.getId(),admin.getRoleIds());
        return R.ok().message("添加成功");
    }

    @PreAuthorize("@me.hasAuthority('system:user:edit')")
    @ApiOperation("修改用户信息")
    @PutMapping("/edit")
    public R editAdmin(
        @ApiParam(name = "admin",value = "要修改的用户信息",required = true)
        @RequestBody Admin admin
    ){
        String id = admin.getId();
        //删除用户角色
        adminRoleService.remove(
                new LambdaQueryWrapper<AdminRole>()
                .eq(AdminRole::getAdminId,id));
        //重新添加用户角色
        this.insertUserRole(id,admin.getRoleIds());
        //更新
        adminService.updateById(admin);
        return R.ok().message("修改用户信息成功");
    }

    @PreAuthorize("@me.hasAuthority('system:user:edit')")
    @ApiOperation("修改用户状态")
    @PutMapping("/changeStatus")
    public R changeStatus(
            @ApiParam(name = "admin",value = "要修改的用户信息",required = true)
            @RequestBody Admin admin
    ){
        adminService.updateById(admin);
        return R.ok().message("更改状态成功");
    }

    @PreAuthorize("@me.hasAuthority('system:user:query')")
    @ApiOperation("根据id查询用户")
    @GetMapping("/{id}")
    public R getAdminById(
            @ApiParam(name = "id",value = "用户id",required = true)
            @PathVariable String id
    ){
        Admin admin = adminService.getById(id);
        //查询用户角色ids
        List<Integer> roleIds = adminService.getRoleIds(id);
        admin.setRoleIds(roleIds);
        return R.ok().data("admin",admin);
    }

    @PreAuthorize("@me.hasAuthority('system:user:remove')")
    @ApiOperation("根据id删除用户")
    @DeleteMapping("/{id}")
    public R removeAdmin(
            @ApiParam(name = "id",value = "要删除的用户id",required = true)
            @PathVariable String[] id
    ){
        //不能删除自己
        if (Arrays.asList(id).contains(SecurityUtils.getUserId())){
            return R.error().message("当前用户无法删除");
        }
        List<String> asList = Arrays.asList(id);
        //删除用户对应角色关联
        adminRoleService.remove(
                new LambdaQueryWrapper<AdminRole>()
                .in(AdminRole::getAdminId,asList));
        //逻辑删除用户
        adminService.removeAdmin(asList);
        return R.ok().message("删除成功");
    }

    //添加用户对应角色
    public void insertUserRole(String adminId, List<Integer> roleIds){
        if (roleIds.isEmpty()){
            return;
        }
        List<AdminRole> adminRoleList = new ArrayList<>();
        for (Integer roleId : roleIds) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleId);
            adminRoleList.add(adminRole);
        }
        adminRoleService.saveBatch(adminRoleList);
    }
}

