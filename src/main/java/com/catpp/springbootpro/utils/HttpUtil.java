package com.catpp.springbootpro.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * com.catpp.springbootpro.utils
 *
 * @Author cat_pp
 * @Date 2018/10/17
 * @Description
 */
public class HttpUtil {

    /**
     * 获取请求ip
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取请求方式
     * @param request
     * @return
     */
    public static String getRequestType(HttpServletRequest request) {
        if (StringUtils.isNotEmpty(request.getHeader("x-requested-with"))
                && StringUtils.equals(request.getHeader("x-requested-with"), "XMLHttpRequest")) {
            return "ajax请求";
        } else {
            return "普通请求";
        }
    }
}
