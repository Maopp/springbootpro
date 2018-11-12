package com.catpp.rabbitmq.common.config;

import com.catpp.rabbitmq.common.constants.QueueConstants;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * com.catpp.rabbitmq.common.config
 *
 * @Author cat_pp
 * @Date 2018/11/12
 * @Description 消息队列配置类
 */
@Configuration
public class MessageTrustPackageConfiguration {

    /**
     * 消息交换
     *
     * @return
     */
    @Bean
    public DirectExchange messageDirechExchange() {
        return (DirectExchange) ExchangeBuilder.directExchange(QueueConstants.MESSAGE_EXCHANGE)
                .durable(true).build();
    }

    /**
     * 消息队列声明
     *
     * @return
     */
    @Bean
    public Queue messageTrustPackageQueue() {
        return QueueBuilder.durable(QueueConstants.MESSAGE_QUEUE_NAME).build();
    }

    /**
     * 消息绑定
     *
     * @return
     */
    @Bean
    public Binding trustPackageBinding() {
        return BindingBuilder.bind(messageTrustPackageQueue()).to(messageDirechExchange()).with(QueueConstants.MESSAGE_ROUTE_KEY);
    }
}
