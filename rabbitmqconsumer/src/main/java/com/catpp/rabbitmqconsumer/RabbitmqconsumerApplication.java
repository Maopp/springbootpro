package com.catpp.rabbitmqconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * com.catpp.rabbitmqconsumer
 *
 * @Author cat_pp
 * @Date 2018/11/5
 * @Description
 */
@Slf4j
@SpringBootApplication
public class RabbitmqconsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqconsumerApplication.class, args);
        log.info("【【【【【消息队列-消息消费者启动成功】】】】】");
    }
}
