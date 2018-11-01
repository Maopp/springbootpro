package com.catpp.springbootpro.config;

import com.catpp.springbootpro.annotation.paramresovle.CustomerArgumentResolver;
import com.catpp.springbootpro.annotation.security.content.ContentSecurityMethodArgumentResolver;
import com.catpp.springbootpro.interceptor.ContentSecurityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * com.catpp.springbootpro.config
 *
 * @Author cat_pp
 * @Date 2018/10/30
 * @Description springMVC注解配置类
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * 配置内容安全拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ContentSecurityInterceptor()).addPathPatterns("/**");
    }

    /**
     * 添加参数装载
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // 将自定义的参数装载添加到spring托管
        // resolvers.add(new CustomerArgumentResolver());
        List<HandlerMethodArgumentResolver> list = new ArrayList<>();
        list.add(new CustomerArgumentResolver());
        list.add(new ContentSecurityMethodArgumentResolver());
        resolvers.addAll(list);
    }

    /**
     * 配置静态请求试图映射
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/param").setViewName("/parameter-loading/param-logding");
    }
}
