package com.fh.config;

import com.fh.interceptor.IdempotenceInterceptor;
import com.fh.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

//拦截器配置类
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private IdempotenceInterceptor idempotenceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        Arrays.asList("/member/login",
                                "/member/register",
                                "/member/sendSms",
                                "/brand/**",
                                "/category/**",
                                "/product/**")
                        );
        registry.addInterceptor(idempotenceInterceptor)
                .addPathPatterns("/order/**");
    }


}
