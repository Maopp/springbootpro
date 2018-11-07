package com.catpp.rabbitmq.common.config;

import com.catpp.rabbitmq.common.enums.ttl.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * com.catpp.rabbitmq.common.config
 *
 * @Author cat_pp
 * @Date 2018/11/7
 * @Description 消息通知，消息队列配置
 */
@Slf4j
@Configuration
public class MessageTtlConfiguration {

    /**
     * 消息中心实际消费队列交换配置
     * @return
     */
    @Bean
    DirectExchange messageDirect() {
        return (DirectExchange) ExchangeBuilder.directExchange(QueueEnum.MESSAGE_QUEUE.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 消息中心延迟消费交换配置
     * @return
     */
    @Bean
    DirectExchange messageTtlDirect() {
        return (DirectExchange) ExchangeBuilder.directExchange(QueueEnum.MESSAGE_TTL_QUEUE.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 消息中心实际消费队列配置
     * @return
     */
    @Bean
    public Queue messageQueue() {
        return new Queue(QueueEnum.MESSAGE_QUEUE.getName());
    }

    /**
     * 消息中心TTL队列
     * @return
     */
    @Bean
    public Queue messageTtlQueue() {
        return QueueBuilder.durable(QueueEnum.MESSAGE_TTL_QUEUE.getName())
                // 配置到期后转发的交换
                .withArgument("x-dead-letter-exchange", QueueEnum.MESSAGE_QUEUE.getExchange())
                // 配置到期后转发的路由键
                .withArgument("x-dead-letter-routing-key", QueueEnum.MESSAGE_QUEUE.getRoutingKey())
                .build();
    }

    /**
     * 消息中心实际消息交换与队列配置
     * @param messageDirect 消息中心交换配置
     * @param messageQueue 消息中心队列
     * @return
     *
     * 使用注解Qualifier/变量名不同，不然初始化单例bean报错
     */
    @Bean
    // Binding messageBinding(@Qualifier("messageDirect") DirectExchange directExchange, Queue messageQueue) {
    Binding messageBinding(DirectExchange messageDirect, Queue messageQueue) {
        return BindingBuilder.bind(messageQueue).to(messageDirect).with(QueueEnum.MESSAGE_QUEUE.getRoutingKey());
    }

    /**
     * 消息中心TTL消息交换和队列配置
     * @param messageTtlDirect
     * @param messageTtlQueue
     * @return
     */
    @Bean
    // Binding messageTtlBinding(@Qualifier("messageTtlDirect") DirectExchange directExchange, Queue messageTtlQueue) {
    Binding messageTtlBinding(DirectExchange messageTtlDirect, Queue messageTtlQueue) {
        return BindingBuilder.bind(messageTtlQueue).to(messageTtlDirect).with(QueueEnum.MESSAGE_TTL_QUEUE.getRoutingKey());
    }
}
