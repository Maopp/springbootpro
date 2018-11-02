package com.catpp.springbootpro.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * com.catpp.springbootpro.quartz
 *
 * @Author cat_pp
 * @Date 2018/11/1
 * @Description 商品库存检查定时任务
 */
@Slf4j
public class GoodsStockCheckTimer extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("执行库存检查定时任务【分布式：first】，执行时间：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
