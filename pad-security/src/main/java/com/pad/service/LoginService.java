package com.pad.service;

import com.pad.entity.Admin;
import com.pad.response.R;

public interface LoginService {
    R login(Admin admin);
}
