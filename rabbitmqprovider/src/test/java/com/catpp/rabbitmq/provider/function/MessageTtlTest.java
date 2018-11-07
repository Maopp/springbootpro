package com.catpp.rabbitmq.provider.function;

import com.catpp.rabbitmq.common.enums.ttl.QueueEnum;
import com.catpp.rabbitmq.provider.base.BaseTest;
import com.catpp.rabbitmq.provider.rabbitmq.impl.QueueMessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * com.catpp.rabbitmq.provider.function
 *
 * @Author cat_pp
 * @Date 2018/11/7
 * @Description
 */
@Slf4j
public class MessageTtlTest extends BaseTest {

    @Autowired
    private QueueMessageServiceImpl queueMessageService;

    /**
     * 将消息先发送到ttl延迟队列内，当消息到达过期时间后会自动转发到ttl队列内配置的转发Exchange以及RouteKey绑定的队列内完成消息消费。
     */
    @Test
    public void testdelay() {
        queueMessageService.sendMessageTtl("测试延迟消费，写入时间：" +  new Date(),
                QueueEnum.MESSAGE_TTL_QUEUE.getExchange(), QueueEnum.MESSAGE_TTL_QUEUE.getRoutingKey(), 5000L);
    }
}
