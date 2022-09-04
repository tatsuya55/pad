package com.pad.config;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
*定义在类上：@Api
 定义在方法上：@ApiOperation
 定义在参数上：@ApiParam
* */

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(PathSelectors.any()) //匹配所有url
                .paths(Predicates.not(PathSelectors.regex("/error.*"))) //不显示内置错误接口
                .apis(RequestHandlerSelectors.basePackage("com.pad"))
                .build()
                //Lists工具类的newArrayList方法将对象转为ArrayList
                //Swagger-ui上出现Authorize，可以手动点击输入token
                .securitySchemes(Lists.newArrayList(apiKey()));

    }

    /**
     * 构建Authorization验证key
     * @return
     */
    private ApiKey apiKey() {
        //// name 为参数名  keyname是页面传值显示的 keyname， name在swagger鉴权中使用
        return new ApiKey("token", "token","header");//配置输入token的备注 TOKEN_HEADER_STRING = "Authorization"
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("平安贷后台管理系统API文档")
                .description("本文档描述了平安贷后台管理系统接口定义")
                .version("1.0")
                .contact(new Contact("pad", "https://gitee.com/fu_2",
                        "苍盐海F4@gmail.com"))
                .build();
    }
}
