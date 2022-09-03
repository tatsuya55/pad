package com.pad.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private Admin admin;

    private List<String> permissions;

    private List<String> roles;

    public LoginUser(Admin admin, List<String> permissions,List<String> roles) {
        this.admin = admin;
        this.permissions = permissions;
        this.roles = roles;
    }

    //不将auths存入redis
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities!=null){
            return authorities;
        }
        //构造权限角色列表
        authorities = new ArrayList<>();
        //把permissions中的权限信息 封装成SimpleGrantedAuthority 放入GrantedAuthority
        for (String permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
        }
        /*//使用流的方式
        authorities= permissions.stream()
            .map(SimpleGrantedAuthority::new).collect(Collectors.toList());*/
        return authorities;
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
