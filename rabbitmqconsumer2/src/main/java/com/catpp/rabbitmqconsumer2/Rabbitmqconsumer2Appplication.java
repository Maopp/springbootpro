package com.catpp.rabbitmqconsumer2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * com.catpp.rabbitmqconsumer2
 *
 * @Author cat_pp
 * @Date 2018/11/6
 * @Description
 */
@Slf4j
@SpringBootApplication
public class Rabbitmqconsumer2Appplication {

    public static void main(String[] args) {
        SpringApplication.run(Rabbitmqconsumer2Appplication.class, args);
        log.info("【【【【【消息队列-消息消费者：---节点2--- 启动成功】】】】】");
    }
}
