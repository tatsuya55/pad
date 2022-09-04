package com.pad.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置类
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
        .addMapping("/**")  //允许的路径
        .allowedOriginPatterns("*") //允许的域名
        .allowCredentials(true)   //是否允许cookie
        .allowedMethods("*")
        .allowedHeaders("*")
        .maxAge(3600);  //跨域允许时间
    }
}
