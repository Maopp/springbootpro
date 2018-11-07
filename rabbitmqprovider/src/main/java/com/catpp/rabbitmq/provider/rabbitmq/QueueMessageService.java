package com.catpp.rabbitmq.provider.rabbitmq;

import com.catpp.rabbitmq.common.enums.direct.DirectExchangeEnum;
import com.catpp.rabbitmq.common.enums.direct.QueueEnum;
import com.catpp.rabbitmq.common.enums.topic.TopicExchangeEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * com.catpp.rabbitmqprovider.rabbitmq
 *
 * @Author cat_pp
 * @Date 2018/11/5
 * @Description 消息队列业务
 */
public interface QueueMessageService extends RabbitTemplate.ConfirmCallback {

    /**
     * 发送消息到rabbitmq消息队列（directExchange）
     *
     * @param message 消息内容
     * @param directExchangeEnum 交换配置枚举
     * @param queueEnum 队列配置枚举
     * @throws Exception
     */
    void send(Object message, DirectExchangeEnum directExchangeEnum, QueueEnum queueEnum) throws Exception;

    /**
     * 发送消息到rabbitmq消息队列（topicExchange）
     *
     * @param message 消息内容
     * @param topicExchangeEnum 交换配置枚举
     * @param routingKey 路由键
     * @throws Exception
     */
    void send(Object message, TopicExchangeEnum topicExchangeEnum, String routingKey) throws Exception;
}
