package com.catpp.springbootpro.exception;

import com.catpp.springbootpro.common.JsonResult;
import com.catpp.springbootpro.pojo.LoggerInfos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * com.catpp.springbootpro.exception
 *
 * @Author cat_pp
 * @Date 2018/10/24
 * @Description 全局异常捕获类
 *
 * 根据注解捕获，@RestController
 *
 */
@ControllerAdvice(annotations = RestController.class)
@ResponseBody
@Slf4j
public class RestExceptionHandler {

    /**
     * 请求日志实体标识
     */
    private static final String LOGGER_ENTITY = "_logger_entity";

    /**
     * @ExceptionHandler注解用来配置需要拦截的异常类型，默认是全局类型。
     * @ResponseStatus注解用于配置遇到该异常后返回数据时的StatusCode的值，我们这里默认使用值500
     *
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseStatus
    public JsonResult runtimeExceptionHandler(Exception e, HttpServletRequest request) {
        log.error("调用接口发生异常，接口地址：{}，错误信息：{}", request.getRequestURI(), e.getMessage());
        // 直接return的话，日志不会记录异常信息，如果需要日志记录异常信息，需要抛出异常
        // 从request中拿到日志实体类，保存异常数据
        LoggerInfos loggerInfos = (LoggerInfos) request.getAttribute(LOGGER_ENTITY);
        loggerInfos.setExceptionInfo(e.toString());
        return JsonResult.errorException(e.getMessage());
    }
}
