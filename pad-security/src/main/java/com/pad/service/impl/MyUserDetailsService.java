package com.pad.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pad.entity.Admin;
import com.pad.entity.LoginUser;
import com.pad.exceptionhandler.PadException;
import com.pad.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/*
* 用户认证的实现类
* */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        //引入lambda，避免写类似name的硬编码
        wrapper.eq(Admin::getName,username);
        //未被禁用的 才能登录
        wrapper.eq(Admin::getStatus,1);
        wrapper.eq(Admin::getIsDeleted,1);
        Admin admin = adminMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(admin)){
            throw new PadException(HttpStatus.UNAUTHORIZED.value(),"用户名或密码错误");
        }
        //查询用户角色
        List<String> roleList = adminMapper.selectRoleNameByUserId(admin.getId());
        //查询用户权限
        List<String> permissionList = adminMapper.selectPermissionByUserId(admin.getId());
        //将数据封装成UserDetails返回
        return new LoginUser(admin,permissionList,roleList);
    }
}
