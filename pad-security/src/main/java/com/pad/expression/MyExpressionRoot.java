package com.pad.expression;

import com.pad.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限校验方法
 */
@Component("me")
public class MyExpressionRoot {
    /**
     * 验证用户是否具备某权限
     * @param permission 权限字符串
     * @return
     */
    public boolean hasAuthority(String permission){
        //获取当前用户权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        return permissions.contains(permission);
    }
}
