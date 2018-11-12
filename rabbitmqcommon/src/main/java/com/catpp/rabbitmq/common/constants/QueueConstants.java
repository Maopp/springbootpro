package com.catpp.rabbitmq.common.constants;

/**
 * com.catpp.rabbitmq.common.constants
 *
 * @Author cat_pp
 * @Date 2018/11/12
 * @Description 队列常量配置
 */
public interface QueueConstants {

    /**
     * 消息交换
     */
    String MESSAGE_EXCHANGE = "message.direct.exchange";

    /**
     * 消息队列名称
     */
    String MESSAGE_QUEUE_NAME = "message.queue";

    /**
     * 消息路由键
     */
    String MESSAGE_ROUTE_KEY = "message.send";
}
