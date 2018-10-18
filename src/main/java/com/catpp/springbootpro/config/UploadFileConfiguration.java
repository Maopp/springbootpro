package com.catpp.springbootpro.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * com.catpp.springbootpro.config
 *
 * @Author cat_pp
 * @Date 2018/10/18
 * @Description 上传文件大小限制：spring boot默认文件大小为1MB
 */
@Configuration
public class UploadFileConfiguration {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件大小KB,MB
        factory.setMaxFileSize("10MB");
        // 全部文件大小
        factory.setMaxRequestSize("100MB");
        return factory.createMultipartConfig();
    }
}
