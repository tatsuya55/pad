package com.pad.utils;

import com.pad.vo.LoginUser;
import com.pad.exceptionhandler.PadException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtils {

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser()
    {
        try{
            return (LoginUser) getAuthentication().getPrincipal();
        }catch (Exception e){
            throw new PadException( HttpStatus.UNAUTHORIZED.value(),"获取用户信息异常");
        }
    }

    /**
     * 获取用户ID
     **/
    public static String getUserId()
    {
        try{
            return getLoginUser().getAdmin().getId();
        }catch (Exception e){
            throw new PadException( HttpStatus.UNAUTHORIZED.value(),"获取用户ID异常");
        }
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
