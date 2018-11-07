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
 * @Description 用户注册创建账户消费者
 */
@Slf4j
@Component
public class CreateAccountTopicConsumer {

    /**
     * 处理消息：创建账户
     * @param userId
     */
    @RabbitHandler
    @RabbitListener(queues = "register.account.queue")
    public void handlerCreateAccount(String userId) {
        log.info("用户：{}，创建成功，初始化账户信息", userId);
        // 省略初始化账户逻辑
    }
}
