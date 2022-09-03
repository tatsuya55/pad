package com.pad.controller;

import com.pad.entity.Admin;
import com.pad.exceptionhandler.PadException;
import com.pad.response.R;
import com.pad.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户登录")
@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public R login(@ApiParam(value = "后台用户")@RequestBody Admin admin){
        return loginService.login(admin);
    }

    @ApiOperation("退出接口")
    @GetMapping("/logout")
    public R logout(){
        return loginService.logout();
    }
}
