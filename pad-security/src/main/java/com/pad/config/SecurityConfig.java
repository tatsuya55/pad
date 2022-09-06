package com.pad.config;

import com.pad.filter.JwtAuthenticationTokenFilter;
import com.pad.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    //注入PersistentTokenRepository 实现记住我
    @Autowired
    private PersistentTokenRepository tokenRepository;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    //指定密码加解密方式
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //认证相关的核心接口 有多种认证方式实现类
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置白名单
        http
        .csrf().disable() //关闭csrf防护
        //不通过session获取SecurityContext
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        //.antMatchers("/a").hasAuthority("a") //配置文件中指定权限
        .antMatchers("/user/login").anonymous() //对于登录接口 允许匿名访问
        .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()
        .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**").permitAll()
        .anyRequest().authenticated();//其他所有请求全部需要鉴权认证

        //设置jwt认证过滤器
        http
        //指定添加的过滤器 及添加的位置
        .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //配置异常处理器
        http
        .exceptionHandling()
        .authenticationEntryPoint(authenticationEntryPoint)
        .accessDeniedHandler(accessDeniedHandler);

        //记住我
        http
        .rememberMe()
        .rememberMeParameter("rememberMe")
        .rememberMeCookieName("rememberMe")
        .tokenValiditySeconds(3600) //指定有效时间 默认两周
        .tokenRepository(tokenRepository)
        .userDetailsService(userDetailsService);

        //注销  注销后persistent_logins表中记录删除 记住我失效
        http
        .logout()
        .logoutUrl("/logout") //注销时访问的路径
        .logoutSuccessUrl("/user/logout") //注销成功时访问的路径
        .permitAll();

        //允许跨域
        http.cors();
    }
}
