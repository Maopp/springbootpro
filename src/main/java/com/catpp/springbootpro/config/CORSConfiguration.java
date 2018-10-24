package com.catpp.springbootpro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * com.catpp.springbootpro.config
 *
 * @Author cat_pp
 * @Date 2018/10/24
 * @Description CORS跨域请求配置类
 */
@Configuration
public class CORSConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 配置可以被跨域的路径
                .allowedMethods("*") // 允许是所有的请求方法访问该跨域资源服务器，如POST、GET、DELETE、PUT
                .allowedOrigins("*") // 允许所有的请求域名访问跨域资源，可以固定单条/多条内容，如：www.baidu.com
                .allowedHeaders("*") // 允许所有的请求header访问，可以自定义设置请求头信息，如"catpp_cors"
                .allowCredentials(false); // 可以不需要配置
    }
}
