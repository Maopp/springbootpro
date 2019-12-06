package com.catpp.springbootpro.service;

import lombok.Setter;

/**
 * com.catpp.springbootpro.service
 *
 * @Author cat_pp
 * @Date 2019/12/6
 * @Description
 */
@Setter
public class HelloService {

    private String msg;

    private boolean show = false;

    public String sayHello() {
        return show ? "hello," + msg : "Hidden";
    }

}
