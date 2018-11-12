package com.catpp.rabbitmq.provider.controller;

import com.catpp.rabbitmq.common.entity.MessageEntity;
import com.catpp.rabbitmq.provider.pojo.SysUser;
import com.catpp.rabbitmq.provider.rabbitmq.QueueMessageService;
import com.catpp.rabbitmq.provider.service.SysUserService;
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
    @Autowired
    private QueueMessageService queueMessageService;

    @RequestMapping("/saveUser")
    public SysUser save(SysUser sysUser) throws Exception {
        userService.save(sysUser);
        return sysUser;
    }

    @RequestMapping("/trustPackage")
    public String trustPackage(MessageEntity messageEntity) {
        queueMessageService.sendMessageTrustPackage(messageEntity);
        return "success";
    }
}
