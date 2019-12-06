package com.catpp.springbootpro.autoconfig;

import com.catpp.springbootpro.common.JsonResult;
import com.catpp.springbootpro.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.catpp.springbootpro.autoconfig
 *
 * @Author cat_pp
 * @Date 2018/10/25
 * @Description 自定义starter自动化配置controller
 */
@RestController
public class HelloAutoConfigController {

    /**
     * 注入自动化配置逻辑类
     */
    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello")
    public JsonResult sayHello() {
        String result = helloService.sayHello();
        return JsonResult.ok(result);
    }
}
