package com.catpp.springbootpro.quartz;

import com.catpp.springbootpro.mapper.GoodInfoMapper;
import com.catpp.springbootpro.pojo.GoodInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * com.catpp.springbootpro.quartz
 *
 * @Author cat_pp
 * @Date 2018/11/1
 * @Description 商品更新定时任务
 */
@Slf4j
public class GoodsUpdateTimer extends QuartzJobBean{

    @Autowired
    private GoodInfoMapper goodInfoMapper;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 更新商品名称，验证定时任务是否执行
        GoodInfo goodInfo = new GoodInfo();
        goodInfo.setId(9);
        goodInfo.setName("test timer");
        goodInfoMapper.updateByPrimaryKey(goodInfo);
        log.info("商品添加完成后执行更新任务，任务时间：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
