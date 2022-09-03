package com.pad.controller;


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
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/test")
    public String test(){
        return "aaa";
    }

}

