package com.catpp.springbootpro.annotation.security.content;

import com.catpp.springbootpro.enums.ContentSecurityAway;

import java.lang.annotation.*;

/**
 * com.catpp.springbootpro.annotation.security.content
 *
 * @Author cat_pp
 * @Date 2018/10/30
 * @Description 配置开启安全验证，添加这个注解可以限制不是所有的请求都做这个处理
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ContentSecurity {

    ContentSecurityAway away() default ContentSecurityAway.DES;
}
