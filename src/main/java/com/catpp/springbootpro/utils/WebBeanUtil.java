package com.catpp.springbootpro.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * com.catpp.springbootpro.utils
 *
 * @Author cat_pp
 * @Date 2018/10/17
 * @Description
 */
public class WebBeanUtil{

    public static <T> T getBean(Class<T> clazz, HttpServletRequest request) {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return context.getBean(clazz);
    }
}
