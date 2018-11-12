package com.catpp.rabbitmq.provider.rabbitmq.impl;

import com.alibaba.fastjson.JSON;
import com.catpp.rabbitmq.common.constants.QueueConstants;
import com.catpp.rabbitmq.common.enums.topic.TopicExchangeEnum;
import com.catpp.rabbitmq.provider.rabbitmq.QueueMessageService;
import com.catpp.rabbitmq.common.enums.direct.DirectExchangeEnum;
import com.catpp.rabbitmq.common.enums.direct.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
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
    /**
     * RabbitMQ 模板消息实现类
     */
    @Autowired
    private AmqpTemplate amqpTemplate;

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
        // 设置回调为当前类对象
        rabbitTemplate.setConfirmCallback(this);
        // 构建回调id为uuid
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString().replaceAll("-", ""));
        // 发送消息到消息队列
        rabbitTemplate.convertAndSend(topicExchangeEnum.getName(), routingKey, message, correlationData);
    }

    @Override
    public void sendMessageTtl(Object message, String exchange, String routingKey, long delayTimes) {
        if (StringUtils.isNotEmpty(exchange)) {
            log.info("延迟：{}毫秒写入消息队列：{}，消息内容：{}", delayTimes, routingKey, message);
            // 执行发送消息到指定队列
            amqpTemplate.convertAndSend(exchange, routingKey, message, message1 -> {
                // 设置延迟毫秒数
                message1.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                return message1;
            });
        } else {
            log.error("未找到队列消息：{} 所属的交换机", exchange);
        }
    }

    @Override
    public void sendMessageTrustPackage(Object object) {
        log.info("写入消息队列内容：{}", JSON.toJSONString(object));
        amqpTemplate.convertAndSend(QueueConstants.MESSAGE_EXCHANGE, QueueConstants.MESSAGE_ROUTE_KEY, object);
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
