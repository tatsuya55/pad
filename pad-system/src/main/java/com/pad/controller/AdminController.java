package com.pad.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    public String test(){
        return "aaa";
    }

}

