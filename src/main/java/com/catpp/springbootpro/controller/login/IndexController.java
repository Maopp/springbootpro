package com.catpp.springbootpro.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * com.catpp.springbootpro.controller.login
 *
 * @Author cat_pp
 * @Date 2018/10/9
 * @Description
 */
@Controller
@RequestMapping("/user")
public class IndexController {

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "/login/login";
    }

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }
}
