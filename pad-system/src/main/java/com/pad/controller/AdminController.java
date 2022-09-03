package com.pad.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 管理员信息表 前端控制器
 * </p>
 *
 * @author F4
 * @since 2022-09-02
 */
@Api(tags = "测试")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @ApiOperation("测试接口")
    @GetMapping("/test")
    //SpEL表达式语法 @me是一个自己配置的spring容器起的别名，能够正确的找到这个容器类
    @PreAuthorize("@me.hasAuthority('company')")
    //@PreAuthorize("hasAuthority('company11')")
    //拥有test权限的才能访问
    public String test(){
        return "aaa";
    }

}

