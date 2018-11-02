package com.catpp.springbootpro.service.impl;

import com.catpp.springbootpro.mapper.GoodInfoMapper;
import com.catpp.springbootpro.pojo.GoodInfo;
import com.catpp.springbootpro.quartz.GoodsAddTimer;
import com.catpp.springbootpro.quartz.GoodsSecKillRemindTimer;
import com.catpp.springbootpro.quartz.GoodsStockCheckTimer;
import com.catpp.springbootpro.quartz.GoodsUpdateTimer;
import com.catpp.springbootpro.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * com.catpp.springbootpro.service.impl
 *
 * @Author cat_pp
 * @Date 2018/11/1
 * @Description
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodInfoMapper goodInfoMapper;
    // 注入任务调度器
    @Autowired
    private Scheduler scheduler;

    @Override
    public Integer save(GoodInfo goodInfo) throws Exception {
        log.info("商品保存时间：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        goodInfoMapper.save(goodInfo);
        // 构建商品添加定时任务（暂时关闭定时任务）
        /*buildCreateGoodsTimer();*/
        // 构建商品库存检查定时任务（暂时关闭定时任务）
        /*buildGoodsStockTimer();*/
        // 构建商品秒杀提醒定时任务（暂时关闭定时任务）
        /*buildGoodsSecKillRemindTimer(goodInfo.getId());*/
        return goodInfo.getId();
    }

    /**
     * 构建商品添加定时任务
     *
     * 我们通过JobDetail来构建一个任务实例，设置GoodAddTimer类作为任务运行目标对象，当任务被触发时就会执行GoodAddTimer内
     * 的executeInternal方法。
     * 一个任务需要设置对应的触发器，触发器也分为很多种，该任务中我们并没有采用cron表达式来设置触发器，而是调用startAt方法
     * 设置任务开始执行时间。
     * 最后将任务以及任务的触发器共同交付给任务调度器，这样就完成了一个任务的设置。
     *
     * @throws Exception
     */
    public void buildCreateGoodsTimer() throws Exception {
        // 设置开始时间为10秒后
        long startAtTime = System.currentTimeMillis() + 1000 * 10;
        // 任务名称
        String name = "商品添加-" + UUID.randomUUID().toString().replaceAll("-", "");
        // 任务所属分组
        String group = GoodsAddTimer.class.getName();
        // 创建任务
        JobDetail jobDetail = JobBuilder.newJob(GoodsUpdateTimer.class).withIdentity(name, group).build();
        // 创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).startAt(new Date(startAtTime)).build();
        // 将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 构建商品库存检查定时任务
     * @throws Exception
     */
    public void buildGoodsStockTimer() throws Exception {
        // 任务名称
        String name = "商品库存检查-" + UUID.randomUUID().toString().replaceAll("-", "");
        // 任务所属分组
        String group = GoodsStockCheckTimer.class.getName();
        // 创建任务
        JobDetail jobDetail = JobBuilder.newJob(GoodsStockCheckTimer.class).withIdentity(name, group).build();
        // 创建任务触发器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(cronScheduleBuilder).build();
        // 将触发器和任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 商品秒杀提醒定时任务
     * @param goodsId
     * @throws Exception
     */
    public void buildGoodsSecKillRemindTimer(Integer goodsId) throws Exception {
        // 任务名称
        String name = "商品秒杀提醒-" + UUID.randomUUID().toString().replaceAll("-", "");
        // 任务所属分组
        String group = GoodsSecKillRemindTimer.class.getName();
        // 秒杀开始时间：添加商品30秒之后
        long startTime = System.currentTimeMillis() + 1000 * 30;
        // 创建任务
        JobDetail jobDetail = JobBuilder.newJob(GoodsSecKillRemindTimer.class).withIdentity(name, group).build();
        // 设置任务传递商品编号参数
        jobDetail.getJobDataMap().put("goodsId", Integer.toString(goodsId));
        // 创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).startAt(new Date(startTime)).build();
        // 将触发器和任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
