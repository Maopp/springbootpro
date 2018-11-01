package com.catpp.springbootpro.controller.param;

import com.alibaba.fastjson.JSON;
import com.catpp.springbootpro.annotation.security.content.ContentSecurity;
import com.catpp.springbootpro.annotation.security.content.ContentSecurityAttribute;
import com.catpp.springbootpro.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * com.catpp.springbootpro.controller.param
 *
 * @Author cat_pp
 * @Date 2018/10/31
 * @Description 参数安全验证controller
 */
@Slf4j
@RestController
public class ContentSecurityController {

    @RequestMapping("/contentSecurity")
    @ContentSecurity
    public String contentSecurity(@ContentSecurityAttribute("student") @Valid Student student) {
        log.info(String.valueOf(JSON.toJSON(student)));
        return "SUCCESS";
    }
}
