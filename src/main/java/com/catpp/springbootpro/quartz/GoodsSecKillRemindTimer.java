package com.catpp.springbootpro.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * com.catpp.springbootpro.quartz
 *
 * @Author cat_pp
 * @Date 2018/11/2
 * @Description 商品秒杀提醒定时任务
 *
 * 当我们添加商品后自动添加一个商品提前五分钟的秒杀提醒，为关注该商品的用户发送提醒消息。
 */
@Slf4j
public class GoodsSecKillRemindTimer extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 获取任务详情内的数据集合
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        // 获取商品编号
        Integer goodsId = dataMap.getIntegerFromString("goodsId");
        // 测试打印信息
        log.info("分布式节点quartz-cluster-node-first，开始处理秒杀商品：{}，提醒注用户推送消息。", goodsId);
        // 省略其他业务处理逻辑
    }
}
