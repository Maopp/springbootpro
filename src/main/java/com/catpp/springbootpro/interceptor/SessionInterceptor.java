package com.catpp.springbootpro.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * com.catpp.springbootpro.interceptor
 *
 * @Author cat_pp
 * @Date 2018/10/9
 * @Description
 */
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果是登陆不做拦截
        String url = request.getRequestURI();
        if (StringUtils.equals(url, "/user/login") || StringUtils.equals(url, "/user/toLogin")) {
            return true;
        }
        // 验证session是否有效
        Object user = request.getSession().getAttribute("user");
        if (null != user) {
            return true;
        } else {
            // 重定向到登陆页面
            response.sendRedirect("/user/toLogin");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
