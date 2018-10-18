package com.catpp.springbootpro.config;

import com.catpp.springbootpro.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * com.catpp.springbootpro.config
 *
 * @Author cat_pp
 * @Date 2018/10/9
 * @Description
 */
// @Configuration
public class SessionConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**");
    }
}
