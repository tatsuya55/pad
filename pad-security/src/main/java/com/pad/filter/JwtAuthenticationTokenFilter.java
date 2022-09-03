package com.pad.filter;

import com.pad.entity.LoginUser;
import com.pad.exceptionhandler.PadException;
import com.pad.utils.JwtUtils;
import com.pad.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt认证过滤器
 * OncePerRequestFilter :security提供过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //从请求头中获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)){
            //没有携带token 直接放行 后面的过滤器会判断是否是不需要认证的
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        Claims claims = JwtUtils.parseJWT(token);
        String userId = claims.getSubject();
        //从redis获取用户信息
        String redisKey = "login:" + userId;
        LoginUser loginUser = (LoginUser) redisUtil.getCacheObject(redisKey);
        if (ObjectUtils.isEmpty(loginUser)){
            throw new PadException(HttpStatus.FORBIDDEN.value(),"用户未登录");
        }
        //存入securityContextHolder 后面的过滤器都是从这里获取信息
        //TODO 获取权限信息
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request,response);
    }
}
