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
 * @Description 商品添加定时任务类
 *
 * 继承org.springframework.scheduling.quartz.QuartzJobBean抽象类，重写父抽象类内的executeInternal方法来实现任务的主体逻辑
 */
@Slf4j
public class GoodsAddTimer extends QuartzJobBean{

    /**
     * 定时任务实现逻辑方法，每当触发器触发时执行该方法
     * @param jobExecutionContext 任务执行上下文
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("商品添加完成后执行任务，任务时间：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
