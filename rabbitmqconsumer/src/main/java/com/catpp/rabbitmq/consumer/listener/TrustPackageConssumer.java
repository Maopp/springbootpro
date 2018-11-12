package com.catpp.rabbitmq.consumer.listener;

import com.alibaba.fastjson.JSON;
import com.catpp.rabbitmq.common.constants.QueueConstants;
import com.catpp.rabbitmq.common.entity.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * com.catpp.rabbitmq.consumer.listener
 *
 * @Author cat_pp
 * @Date 2018/11/12
 * @Description 消息队列-消息消费者
 */
@Slf4j
@Component
public class TrustPackageConssumer {

    @RabbitHandler
    @RabbitListener(queues = QueueConstants.MESSAGE_QUEUE_NAME)
    public void handler(@Payload MessageEntity messageEntity) {
        log.info("消费内容：{}", JSON.toJSONString(messageEntity));
    }
}
