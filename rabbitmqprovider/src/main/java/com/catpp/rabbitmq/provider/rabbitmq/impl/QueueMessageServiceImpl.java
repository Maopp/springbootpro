package com.catpp.rabbitmq.provider.rabbitmq.impl;

import com.catpp.rabbitmq.common.enums.topic.TopicExchangeEnum;
import com.catpp.rabbitmq.provider.rabbitmq.QueueMessageService;
import com.catpp.rabbitmq.common.enums.direct.DirectExchangeEnum;
import com.catpp.rabbitmq.common.enums.direct.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * com.catpp.rabbitmqprovider.rabbitmq.impl
 *
 * @Author cat_pp
 * @Date 2018/11/5
 * @Description 消息队列业务逻辑
 */
@Slf4j
@Component
public class QueueMessageServiceImpl implements QueueMessageService {

    /**
     * 消息队列模板
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(Object message, DirectExchangeEnum directExchangeEnum, QueueEnum queueEnum) throws Exception {
        // 设置回调为当前类对象
        rabbitTemplate.setConfirmCallback(this);
        // 构建回调id为uuid
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString().replaceAll("-", ""));
        // 发送消息到消息队列
        rabbitTemplate.convertAndSend(directExchangeEnum.getValue(), queueEnum.getRoutingKey(), message, correlationData);
    }

    @Override
    public void send(Object message, TopicExchangeEnum topicExchangeEnum, String routingKey) throws Exception {
        // 不调用回调确认函数
        // 发送消息到消息队列
        rabbitTemplate.convertAndSend(topicExchangeEnum.getName(), routingKey, message);
    }

    /**
     * 消息回调确认方法
     * @param correlationData 请求数据对象
     * @param b 是否发送成功
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String cause) {
        log.info("回调id：{}", correlationData.getId());
        if (b) {
            log.info("消息发送成功");
        } else {
            log.info("消息发送失败");
        }
    }
}
