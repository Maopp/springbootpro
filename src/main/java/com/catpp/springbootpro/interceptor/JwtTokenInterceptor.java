package com.catpp.springbootpro.interceptor;

import com.catpp.springbootpro.mapper.JwtTokenMapper;
import com.catpp.springbootpro.pojo.JwtToken;
import com.catpp.springbootpro.utils.WebBeanUtil;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.SignatureException;

/**
 * com.catpp.springbootpro.interceptor
 *
 * @Author cat_pp
 * @Date 2018/10/22
 * @Description token拦截器
 */
public class JwtTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 过滤生成token路径，如果时options请求是cors跨域预请求，设置allow对应头信息
        if (request.getRequestURI().equals("/token") || RequestMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }
        // 其他请求获取头信息
        final String authHeader = request.getHeader("access_token");
        try {
            // 如果请求头信息为空
            if (StringUtils.isEmpty(authHeader) || StringUtils.isEmpty(authHeader.trim())) {
                throw new SignatureException("not found access_token");
            }
            // 获取jwt实体对象接口实例
            final Claims claims = Jwts.parser().setSigningKey("catppAuth")
                    .parseClaimsJws(authHeader).getBody();
            // 从数据库中获取token
            JwtToken token = WebBeanUtil.getBean(JwtTokenMapper.class, request).selectOneByAppId(claims.getSubject());
            // 数据库中的token值
            String tokenVal = new String(token.getToken());
            // 如果内存中不存在，提示客户端获取token
            if (StringUtils.isEmpty(tokenVal) || StringUtils.isEmpty(tokenVal.trim())) {
                throw new SignatureException("not found token info, please get token again");
            }
            // 判断内存中的token与客户端传过来的是否一样
            if (!StringUtils.equals(authHeader, tokenVal)) {
                throw new SignatureException("not found token info, please get token again");
            }
        } catch (SignatureException | ExpiredJwtException e) {
            PrintWriter writer = response.getWriter();
            writer.write("need refresh token");
            writer.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
