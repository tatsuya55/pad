package com.pad.service;

import com.pad.entity.Admin;
import com.pad.response.R;

public interface LoginService {
    //登录
    R login(Admin admin);
    //退出
    R logout();
}
