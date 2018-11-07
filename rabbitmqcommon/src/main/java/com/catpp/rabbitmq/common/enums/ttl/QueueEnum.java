package com.catpp.rabbitmq.common.enums.ttl;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * com.catpp.rabbitmq.common.enums.ttl
 *
 * @Author cat_pp
 * @Date 2018/11/7
 * @Description 消息队列枚举配置
 */
@Getter
@AllArgsConstructor
public enum QueueEnum {

    /**
     * 消息通知队列
     */
    MESSAGE_QUEUE("message.center.direct", "message.center.create.queue", "message.center.create"),

    /**
     * 消息通知ttl队列
     */
    MESSAGE_TTL_QUEUE("message.center.direct.ttl", "message.center.create.ttl.queue", "message.center.create.ttl");

    /**
     * 交换名称
     */
    private String exchange;

    /**
     * 队列名称
     */
    private String name;

    /**
     * 路由键
     */
    private String routingKey;
}
