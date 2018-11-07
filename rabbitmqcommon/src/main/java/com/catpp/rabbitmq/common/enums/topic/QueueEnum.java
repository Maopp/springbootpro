package com.catpp.rabbitmq.common.enums.topic;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * com.catpp.rabbitmq.common.enums.topic
 *
 * @Author cat_pp
 * @Date 2018/11/7
 * @Description 队列配置枚举
 */
@Getter
@AllArgsConstructor
public enum QueueEnum {

    /**
     * 用户注册，账户消息队列
     */
    USER_REGISTER_CREATE_ACCOUNT("register.account.queue", "register.#"),

    /**
     * 用户注册，发送注册成功邮件消息队列
     */
    USER_REGISTER_SEND_MAIL("register.mail.queue", "register.#");

    /**
     * 消息队列名称
     */
    private String name;

    /**
     * 消息路由key
     */
    private String routingKey;
}
