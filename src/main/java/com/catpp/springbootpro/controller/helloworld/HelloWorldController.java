package com.catpp.springbootpro.controller.helloworld;

import com.catpp.springbootpro.common.JsonResult;
import com.catpp.springbootpro.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.catpp.springbootpro.controller.helloworld
 *
 * @Author cat_pp
 * @Date 2018/10/8
 * @Description
 */
@RestController
@RequestMapping("/helloworld")
public class HelloWorldController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/test")
    public String test() {
        return "hello world";
    }

    @RequestMapping("/user")
    public JsonResult getUser() {
        return JsonResult.ok(sysUserService.selectOne(1));
    }
}
