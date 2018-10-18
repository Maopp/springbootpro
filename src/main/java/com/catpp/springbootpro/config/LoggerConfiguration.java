package com.catpp.springbootpro.config;

import com.catpp.springbootpro.interceptor.LoggerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * com.catpp.springbootpro.config
 *
 * @Author cat_pp
 * @Date 2018/10/17
 * @Description
 */
@Configuration
public class LoggerConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public LoggerInterceptor loggerInterceptor() {
        return new LoggerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**");
    }
}
