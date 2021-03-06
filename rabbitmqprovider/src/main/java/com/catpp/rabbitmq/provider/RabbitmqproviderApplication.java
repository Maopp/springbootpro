package com.catpp.rabbitmq.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * com.catpp.rabbitmq.provider
 *
 * @Author cat_pp
 * @Date 2018/11/5
 * @Description
 */
@Slf4j
// 扫描mybatis mapper 包路径
@MapperScan(basePackages = "com.catpp.rabbitmq.provider.mapper")
// 默认扫描启动类所在包和子包
@ComponentScan(value = {"com.catpp.rabbitmq"})
@SpringBootApplication
public class RabbitmqproviderApplication {

    /**
     * 消息队列提供者启动入口
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqproviderApplication.class, args);
        log.info("【【【【【消息队列-消息提供者启动成功】】】】】");
    }
}
