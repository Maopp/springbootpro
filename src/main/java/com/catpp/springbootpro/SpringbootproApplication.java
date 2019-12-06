package com.catpp.springbootpro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@Slf4j
@SpringBootApplication
// 扫描mybatis mapper 包路径
@MapperScan(basePackages = "com.catpp.springbootpro.mapper")
// 默认扫描启动类所在包和子包
@ComponentScan("com.catpp.springbootpro")
// 使用定时任务自动配置
@EnableScheduling
// 开启缓存，自动配置配置文件的配置信息进行条件注入缓存所需实例
@EnableCaching
// 开启自动化配置
@EnableAutoConfiguration
public class SpringbootproApplication {

	public static void main(String[] args) {

		/**
		 * 原始启动方式
 		 */
		SpringApplication.run(SpringbootproApplication.class, args);

		log.info("【【【【【【定时任务分布式节点 - quartz-cluster-node-first 已启动】】】】】】");

		/**
		 * 隐藏Banner启动方式
		 */
		/*SpringApplication springApplication = new SpringApplication(SpringbootproApplication.class);
		// 设置banner的模式为隐藏
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);*/
	}
}
