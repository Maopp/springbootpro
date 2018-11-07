package com.catpp.rabbitmq.common.config;

import com.catpp.rabbitmq.common.enums.topic.QueueEnum;
import com.catpp.rabbitmq.common.enums.topic.TopicExchangeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * com.catpp.rabbitmq.common.config
 *
 * @Author cat_pp
 * @Date 2018/11/7
 * @Description 用户注册消息队列topicExchange配置
 */
@Slf4j
@Configuration
public class UserRegisterQueueTopicExchangeConfiguration {

    /**
     * 配置用户注册主题交换
     * @return
     */
    @Bean
    public TopicExchange userTopicExchange() {
        TopicExchange topicExchange = new TopicExchange(TopicExchangeEnum.USER_REGISTER.getName());
        log.info("【用户注册交换】实例化成功");
        return topicExchange;
    }

    /**
     * 配置用户注册发送成功邮件消息队列，设置持久化队列
     * @return
     */
    @Bean
    public Queue sendRegisterMailQueue() {
        Queue queue = new Queue(QueueEnum.USER_REGISTER_SEND_MAIL.getName(), true);
        log.info("创建用户注册【发送成功邮件】队列成功");
        return queue;
    }

    /**
     * 配置用户注册创建账户消息队列，设置持久化队列
     * @return
     */
    @Bean
    public Queue createAccountQueue() {
        Queue queue = new Queue(QueueEnum.USER_REGISTER_CREATE_ACCOUNT.getName(), true);
        log.info("创建用户注册【创建账户】队列成功");
        return queue;
    }

    /**
     * 绑定发送注册成功邮件到用户注册主题交换配置
     * @param userTopicExchange
     * @param sendRegisterMailQueue
     * @return
     */
    @Bean
    public Binding sendMailBinding(TopicExchange userTopicExchange, Queue sendRegisterMailQueue) {
        Binding binding = BindingBuilder.bind(sendRegisterMailQueue).to(userTopicExchange).with(QueueEnum.USER_REGISTER_SEND_MAIL.getRoutingKey());
        log.info("绑定【发送邮件到注册交换】成功");
        return binding;
    }

    /**
     * 绑定创建账户到用户注册主题交换配置
     * @param userTopicExchange
     * @param createAccountQueue
     * @return
     */
    @Bean
    public Binding createAccountBinding(TopicExchange userTopicExchange, Queue createAccountQueue) {
        Binding binding = BindingBuilder.bind(createAccountQueue).to(userTopicExchange).with(QueueEnum.USER_REGISTER_CREATE_ACCOUNT.getRoutingKey());
        log.info("绑定【创建账户到注册交换】成功");
        return binding;
    }

}
