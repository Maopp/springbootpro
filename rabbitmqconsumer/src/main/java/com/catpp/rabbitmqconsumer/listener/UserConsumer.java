package com.catpp.rabbitmqconsumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * com.catpp.rabbitmqconsumer.listener
 *
 * @Author cat_pp
 * @Date 2018/11/5
 * @Description 用户注册消息消费者
 *
 * 完成消息监听，消息消费
 */
@Slf4j
@Component
@RabbitListener(queues = "user.register.queue")
// @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "user.register.queue"), exchange = @Exchange(value = "user.register.topic.exchange"), key = "user.register")})
public class UserConsumer {

    @RabbitHandler
    public void execute(Integer userId) {
        log.info("用户：【{}】完成了注册", userId);
    }
}
