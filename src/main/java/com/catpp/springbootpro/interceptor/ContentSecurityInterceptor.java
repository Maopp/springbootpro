package com.catpp.springbootpro.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.catpp.springbootpro.annotation.security.constants.ContentSecurityConstants;
import com.catpp.springbootpro.annotation.security.content.ContentSecurity;
import com.catpp.springbootpro.utils.AESUtil;
import com.catpp.springbootpro.utils.DES3Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * com.catpp.springbootpro.interceptor
 *
 * @Author cat_pp
 * @Date 2018/10/30
 * @Description 安全认证拦截器
 */
@Slf4j
public class ContentSecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 默认可以通过
        boolean isPass = true;
        // 获取请求映射方法对象
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 获取访问方法实例对象
        Method method = handlerMethod.getMethod();
        // 检查是否存在内容安全验证注解
        ContentSecurity contentSecurity = method.getAnnotation(ContentSecurity.class);
        // 存在注解做出不同方式认证处理
        if (null != contentSecurity) {
            switch (contentSecurity.away()) {
                // DES方式内容加密处理
                case DES:
                    isPass = checkDES(request, response);
                    break;
                case AES:
                    isPass = checkAES(request, response);
            }
        }
        return true;
    }

    /**
     * 检查AES方式内容
     * @param request
     * @param response
     * @return
     */
    private boolean checkAES(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取aesString加密内容
        String aes = request.getParameter(ContentSecurityConstants.AES_PARAMETER_NAME);
        log.info("请求加密参数内容：{}", aes);
        // 加密串不存在
        if (StringUtils.isEmpty(aes)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", "The AES Content Security Away Request , Parameter Required is "
                    + ContentSecurityConstants.AES_PARAMETER_NAME);
            response.getWriter().print(JSON.toJSONString(jsonObject));
            return false;
        }
        // 存在加密串，解密AES参数列表并重新添加到request内
        try {
            aes = AESUtil.decrypt(aes);
            if (StringUtils.isNotEmpty(aes)) {
                JSONObject params = JSON.parseObject(aes);
                log.info("解密请求后获得的参数列表：>>> {}", aes);
                Iterator<String> iterator = params.keySet().iterator();
                while (iterator.hasNext()) {
                    // 获取请求参数名称
                    String parameterName = iterator.next().toString();
                    // 参数名称不为空时将值设置到request对象内
                    if (StringUtils.isNotEmpty(parameterName)) {
                        request.setAttribute(ContentSecurityConstants.ATTRIBUTE_PREFIX + parameterName, params.get(parameterName));
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            JSONObject json = new JSONObject();
            json.put("msg", "The AES Content Security Error." + ContentSecurityConstants.AES_PARAMETER_NAME);
            response.getWriter().print(JSON.toJSONString(json));
            return false;
        }
        return true;
    }

    /**
     * 检查DES方式内容
     * @param request
     * @param response
     * @return
     */
    private boolean checkDES(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取desString加密内容
        String des = request.getParameter(ContentSecurityConstants.DES_PARAMETER_NAME);
        log.info("请求加密参数内容：{}", des);
        // 加密串不存在
        if (StringUtils.isEmpty(des)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", "The DES Content Security Away Request , Parameter Required is "
                    + ContentSecurityConstants.DES_PARAMETER_NAME);
            response.getWriter().print(JSON.toJSONString(jsonObject));
            return false;
        }
        // 存在加密串，解密DES参数列表并重新添加到request内
        try {
            des = DES3Util.decrypt(des);
            if (StringUtils.isNotEmpty(des)) {
                JSONObject params = JSON.parseObject(des);
                log.info("解密请求后获得的参数列表：>>> {}", des);
                Iterator<String> iterator = params.keySet().iterator();
                while (iterator.hasNext()) {
                    // 获取请求参数名称
                    String parameterName = iterator.next().toString();
                    // 参数名称不为空时将值设置到request对象内
                    if (StringUtils.isNotEmpty(parameterName)) {
                        request.setAttribute(ContentSecurityConstants.ATTRIBUTE_PREFIX + parameterName, params.get(parameterName));
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            JSONObject json = new JSONObject();
            json.put("msg", "The DES Content Security Error." + ContentSecurityConstants.DES_PARAMETER_NAME);
            response.getWriter().print(JSON.toJSONString(json));
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
