package com.catpp.rabbitmqprovider.controller;

import com.catpp.rabbitmqprovider.pojo.SysUser;
import com.catpp.rabbitmqprovider.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.catpp.rabbitmqprovider.controller
 *
 * @Author cat_pp
 * @Date 2018/11/5
 * @Description
 */
@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private SysUserService userService;

    @RequestMapping("/saveUser")
    public SysUser save(SysUser sysUser) throws Exception {
        userService.save(sysUser);
        return sysUser;
    }
}
