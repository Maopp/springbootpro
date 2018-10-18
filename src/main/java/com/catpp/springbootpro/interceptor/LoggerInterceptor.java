package com.catpp.springbootpro.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.catpp.springbootpro.mapper.LoggerInfosMapper;
import com.catpp.springbootpro.pojo.LoggerInfos;
import com.catpp.springbootpro.service.LoggerInfosService;
import com.catpp.springbootpro.utils.HttpUtil;
import com.catpp.springbootpro.utils.WebBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * com.catpp.springbootpro.interceptor
 *
 * @Author cat_pp
 * @Date 2018/10/17
 * @Description 日志拦截器
 */
public class LoggerInterceptor implements HandlerInterceptor {

    @Autowired
    private LoggerInfosService loggerInfosService;

    /**
     * 请求开始时间标识
     */
    private static final String LOGGER_SEND_TIME = "_send_time";
    /**
     * 请求日志实体标识
     */
    private static final String LOGGER_ENTITY = "_logger_entity";
    /**
     * 返回数据标志
     */
    private static final String LOGGER_RETURN = "_logger_return";

    /**
     * 进入springmvc的controller之前开始记录日志实体
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 创建日志实体
        LoggerInfos loggerInfos = new LoggerInfos();
        // 获取请求sessionId
        String sessionId = request.getRequestedSessionId();
        // 请求路径
        String url = request.getRequestURI();
        // 获取请求参数信息
        String paramData = JSON.toJSONString(request.getParameterMap(),
                SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
        // 设置客户端IP
        loggerInfos.setCilentIp(HttpUtil.getIpAddr(request));
        // 设置请求方法
        loggerInfos.setMethod(request.getMethod());
        // 设置请求类型：ajax/普通请求
        loggerInfos.setType(HttpUtil.getRequestType(request));
        // 设置请求参数内容json字符串
        loggerInfos.setParamData(paramData);
        // 设置请求地址
        loggerInfos.setUri(url);
        // 设置sessionId
        loggerInfos.setSessionId(sessionId);
        // 设置请求开始时间
        request.setAttribute(LOGGER_SEND_TIME, System.currentTimeMillis());
        // 设置请求实体到request内，方便afterCompletion方法调用
        request.setAttribute(LOGGER_ENTITY, loggerInfos);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 返回前台之前
     * @param request 请求对象
     * @param response 响应对象
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 获取请求状态码
        int status = response.getStatus();
        // 当前时间
        long currentTime = System.currentTimeMillis();
        // 请求开始时间
        long time = Long.parseLong(request.getAttribute(LOGGER_SEND_TIME).toString());
        // 获取请求日志实体
        LoggerInfos loggerInfos = (LoggerInfos) request.getAttribute(LOGGER_ENTITY);
        // 设置请求时间差
        loggerInfos.setTimeConsuming(Integer.valueOf((currentTime - time) + ""));
        // 设置返回时间
        loggerInfos.setReturnTime(currentTime + "");
        // 设置返回状态码
        loggerInfos.setHttpStatusCode(status + "");
        // 设置返回值
        loggerInfos.setReturnData(JSON.toJSONString(request.getAttribute(LOGGER_RETURN),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue));
        // todo 注入服务失败，在配置类中注入拦截器没有调试成功
        /*loggerInfosService.save(loggerInfos);*/
        LoggerInfosMapper loggerInfosMapper = (LoggerInfosMapper) WebBeanUtil.getBean(LoggerInfosMapper.class, request);
        loggerInfosMapper.insert(loggerInfos);
    }
}
