package com.catpp.springbootpro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * com.catpp.springbootpro.config
 *
 * @Author cat_pp
 * @Date 2018/10/18
 * @Description 静态资源配置类
 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/catpp/resources/**").addResourceLocations("classpath:/static/");
    }
}
