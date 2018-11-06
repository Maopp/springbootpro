package com.catpp.rabbitmqprovider.base;

import com.catpp.rabbitmqprovider.RabbitmqproviderApplicationTests;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * com.catpp.rabbitmqprovider.base
 *
 * @Author cat_pp
 * @Date 2018/11/6
 * @Description
 */
public class BaseTest extends RabbitmqproviderApplicationTests {

    /**
     * 模拟mvc测试对象
     */
    protected MockMvc mockMvc;

    /**
     * web项目上下文
     */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * 所有测试方法执行之前执行该方法
     */
    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
