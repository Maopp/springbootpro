package com.catpp.rabbitmq.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * com.catpp.rabbitmq.consumer.listener
 *
 * @Author cat_pp
 * @Date 2018/11/7
 * @Description 用户注册发送成功邮件消费者
 */
@Slf4j
@Component
public class SendMailTopicConsumer {

    /**
     * 处理消息：发送注册成功邮件
     * 处理消息：发送注册成功邮件
     * @param userId
     */
    @RabbitHandler
    @RabbitListener(queues = "register.mail.queue")
    public void handlerSendMail(String userId) {
        log.info("用户：{}，注册成功，自动发送注册成功邮件", userId);
        // 省略发送邮件逻辑
    }
}
