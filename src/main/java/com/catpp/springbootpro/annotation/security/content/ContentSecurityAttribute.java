package com.catpp.springbootpro.annotation.security.content;

import java.lang.annotation.*;

/**
 * com.catpp.springbootpro.annotation.security.content
 *
 * @Author cat_pp
 * @Date 2018/10/30
 * @Description
 */
@Target(value = ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ContentSecurityAttribute {

    /**
     * 参数值，对象@ContentSecurityAttribute注解的参数名称
     * @return
     */
    String value();
}
