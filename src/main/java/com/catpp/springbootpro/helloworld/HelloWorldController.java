package com.catpp.springbootpro.helloworld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.catpp.springbootpro.helloworld
 *
 * @Author cat_pp
 * @Date 2018/10/8
 * @Description
 */
@RestController
@RequestMapping("/helloworld")
public class HelloWorldController {

    @RequestMapping("/test")
    public String test() {
        return "hello world";
    }
}
