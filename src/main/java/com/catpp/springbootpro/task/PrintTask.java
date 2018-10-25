package com.catpp.springbootpro.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * com.catpp.springbootpro.task
 *
 * @Author cat_pp
 * @Date 2018/10/25
 * @Description 定时任务
 *
 * 要使用@Component注解交由Spring管理
 *
 */
//@Component
public class PrintTask {


    /**
     * 每五秒触发一次
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void cron() {
        System.err.println("使用定时任务打印数据cron：" + new Date());
    }

    /**
     * 上次调用“开始”之后再次调用的延时时间，不用等到调用完成就可以计时
     * @throws InterruptedException
     */
    @Scheduled(fixedRate = 5000 * 1)
    public void fixedRate() throws InterruptedException {
        Thread.sleep(2000L);
        System.err.println("使用定时任务打印数据fixedRate：" + new Date());
    }

    /**
     * 上次调用“完成”后再次调用的延时时间，需要等到上次调用完成再计时
     * @throws InterruptedException
     */
    @Scheduled(fixedDelay = 5000 * 1)
    public void fixedDelay() throws InterruptedException {
        Thread.sleep(2000L);
        System.err.println("使用定时任务打印数据fixedDelay：" + new Date());
    }

    /**
     * 第一次调用开始之前的延时
     * @throws InterruptedException
     */
    @Scheduled(initialDelay = 5000 * 1, fixedDelay = 2000 * 1)
    public void initial() throws InterruptedException {
        System.err.println("使用定时任务打印数据initialDelay：" + new Date());
    }

}
