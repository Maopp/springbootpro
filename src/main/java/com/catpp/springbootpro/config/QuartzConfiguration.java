package com.catpp.springbootpro.config;

import lombok.extern.slf4j.Slf4j;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;

/**
 * com.catpp.springbootpro.config
 *
 * @Author cat_pp
 * @Date 2018/11/1
 * @Description quratz 定时任务配置类
 */
@Slf4j
@Configuration
@EnableScheduling
public class QuartzConfiguration {

    /**
     * 继承org.springframework.scheduling.quartz.SpringBeanJobFactory，实现任务实例化方式
     */
    public static class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

        private transient AutowireCapableBeanFactory beanFactory;

        @Override
        public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
            beanFactory = applicationContext.getAutowireCapableBeanFactory();
        }

        /**
         * 将job实例交给spring IoC托管
         * 可以在job实例实现类中使用注入的方式调用被spring IoC管理的实例
         *
         * @param bundle
         * @return
         * @throws Exception
         */
        @Override
        protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
            final Object job = super.createJobInstance(bundle);
            // 将job实例交给spring IoC
            beanFactory.autowireBean(job);
            return job;
        }
    }

    /**
     * 配置任务工厂实例
     * @param applicationContext spring 上下文
     * @return
     */
    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        /**
         * 采用自定义任务工厂、整合spring实例来完成构建任务
         * see {@link AutowiringSpringBeanJobFactory}
         */

        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    /**
     * 配置任务调度器
     * 使用项目数据源作为quartz数据源
     * @param jobFactory 自定义配置任务工厂
     * @param dataSource 数据源实例
     * @return
     * @throws Exception
     */
    @Bean(destroyMethod = "destroy", autowire = Autowire.NO)
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, DataSource dataSource) throws Exception {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        // 将spring管理job自定义工厂交由调度器管理
        schedulerFactoryBean.setJobFactory(jobFactory);
        // 设置覆盖已存在的任务
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        // 项目启动完成后，等待2秒后执行调度器初始化
        // schedulerFactoryBean.setStartupDelay(2);
        // 设置调度器自动运行
        schedulerFactoryBean.setAutoStartup(true);
        // 设置数据源，使用与项目统一数据源
        schedulerFactoryBean.setDataSource(dataSource);
        // 设置上下文 spring bean name
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
        // 设置配置文件位置
        schedulerFactoryBean.setConfigLocation(new ClassPathResource("/quartz.properties"));
        log.info("-------------------------------定时任务调度器配置完成-------------------------------");
        return schedulerFactoryBean;
    }
}
