package com.catpp.springbootpro.config;

import com.catpp.springbootpro.annotation.paramresovle.CustomerArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
     * 添加参数装载
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // 将自定义的参数装载添加到spring托管
        resolvers.add(new CustomerArgumentResolver());
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
