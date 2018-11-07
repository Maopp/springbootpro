package com.catpp.rabbitmq.consumer2.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * com.catpp.rabbitmqconsumer2.listener
 *
 * @Author cat_pp
 * @Date 2018/11/6
 * @Description
 */
@Slf4j
@Component
@RabbitListener(queues = "user.register.queue")
public class UserConsumer {

    @RabbitHandler
    public void execute(Integer userId) {
        log.info("用户：【{}】完成了注册", userId);
    }
}
