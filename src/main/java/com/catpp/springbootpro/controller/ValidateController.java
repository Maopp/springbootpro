package com.catpp.springbootpro.controller;

import com.catpp.springbootpro.pojo.ValidateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * com.catpp.springbootpro.controller
 *
 * @Author cat_pp
 * @Date 2018/10/19
 * @Description
 */
@RestController
@RequestMapping("/validate")
public class ValidateController {

    /**
     * hibernate-validator的错误消息支持国际化，使用MessageSource对象进行格式化
     */
    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/test")
    public String testValidate(@Valid ValidateEntity validateEntity, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder msg = new StringBuilder();
            // 获取错误字段集合
            List<FieldError> fieldErrors = result.getFieldErrors();
            // 获取本地Locale，zh_CN
            Locale locale = LocaleContextHolder.getLocale();
            // 遍历错误字段获取错误信息
            for (FieldError fieldError : fieldErrors) {
                // 获取错误信息
                String errorMessage = messageSource.getMessage(fieldError, locale);
                msg.append(fieldError.getField() + ":" + errorMessage + ",");
            }
            return msg.toString();
        }
        return "验证通过，名称：" + validateEntity.getName() + "\n年龄：" +
                validateEntity.getAge() + "\n邮箱：" + validateEntity.getEmail();
    }
}
