package com.pad.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pad.entity.Admin;
import com.pad.entity.AdminRole;
import com.pad.response.R;
import com.pad.service.AdminRoleService;
import com.pad.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private PasswordEncoder passwordEncoder;

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
        Page<Admin> adminPage = new Page<>(current, limit);
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
    public R addAdmin(@ApiParam(name = "admin",value = "添加的用户",required = true)
                      @RequestBody Admin admin){
        //密码加密
        String encode = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encode);
        //添加用户
        adminService.save(admin);
        //添加用户对应角色
        List<Integer> roleIds = admin.getRoleIds();
        if (roleIds.isEmpty()){
            return R.ok().message("添加成功");
        }
        List<AdminRole> adminRoleList = new ArrayList<>();
        for (Integer roleId : roleIds) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(admin.getId());
            adminRole.setRoleId(roleId);
            adminRoleList.add(adminRole);
        }
        adminRoleService.saveBatch(adminRoleList);
        return R.ok().message("添加成功");
    }
}

