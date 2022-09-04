package com.pad.service.impl;

import com.pad.entity.Admin;
import com.pad.entity.LoginUser;
import com.pad.exceptionhandler.PadException;
import com.pad.response.R;
import com.pad.service.LoginService;
import com.pad.utils.JwtUtils;
import com.pad.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public R login(Admin admin) {
        //进行用户认证 使用用户名与密码认证方式
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(admin.getName(),admin.getPassword());
        Authentication authenticate = manager.authenticate(authenticationToken);
        //认证失败 抛出异常
        if (ObjectUtils.isEmpty(authenticate)){
            throw new PadException(-1,"登录失败");
        }
        //认证成功 使用userId生成jwt 存入R
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getAdmin().getId();
        String jwt = JwtUtils.acquireJWT(id);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("token",jwt);
        //将用户信息存入redis
        redisUtil.setCacheObject("login:"+id,loginUser);
        return R.ok().code(200).message("登录成功").data(map);
    }
}
