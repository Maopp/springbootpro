package com.catpp.springbootpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// 扫描mybatis mapper 包路径
@MapperScan(basePackages = "com.catpp.springbootpro.mapper")
// 默认扫描启动类所在包和子包
@ComponentScan("com.catpp.springbootpro")
// 使用定时任务自动配置
@EnableScheduling
public class SpringbootproApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootproApplication.class, args);
	}
}
