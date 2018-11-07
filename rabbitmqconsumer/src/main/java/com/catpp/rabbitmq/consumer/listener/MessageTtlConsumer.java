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
 * @Description 消息通知消费者
 */
@Slf4j
@Component
@RabbitListener(queues = "message.center.create.queue")
public class MessageTtlConsumer {

    @RabbitHandler
    public void handler(String content) {
        log.info("消息通知消费内容：{}", content);
    }
}
