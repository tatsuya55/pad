package com.pad.controller;

import com.pad.entity.Admin;
import com.pad.exceptionhandler.PadException;
import com.pad.response.R;
import com.pad.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    //登录
    @PostMapping("/login")
    public R login(@RequestBody Admin admin){
        return loginService.login(admin);
    }

    //退出
    @GetMapping("/logout")
    public R logout(){
        return loginService.logout();
    }
}
