package com.catpp.springbootpro.base;

import com.catpp.springbootpro.SpringbootproApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * com.catpp.springbootpro.base
 *
 * @Author cat_pp
 * @Date 2018/10/26
 * @Description 测试类基类
 */
@Slf4j
public class BaseTest extends SpringbootproApplicationTests {

    /**
     * 模拟mvc测试对象
     */
    private MockMvc mockMvc;

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

    /**
     * perform方法其实只是为了构建一个请求，并且返回ResultActions实例，该实例则是可以获取到请求的返回内容
     *
     * MockMvcRequestBuilders该抽象类则是可以构建多种请求方式，如：Post、Get、Put、Delete等常用的请求方式，
     * 其中参数则是我们需要请求的本项目的相对路径，/则是项目请求的根路径。
     *
     * param方法用于在发送请求时携带参数，当然除了该方法还有很多其他的方法
     *
     * andReturn方法则是在发送请求后需要获取放回时调用，该方法返回MvcResult对象，
     * 该对象可以获取到返回的视图名称、返回的Response状态、获取拦截请求的拦截器集合等
     */
    @Test
    public void testIndex() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/helloworld/test")// .param("ketty")
        ).andReturn();

        // 相应状态
        int status = mvcResult.getResponse().getStatus();
        // 返回数据转换成字符串
        String responseString = mvcResult.getResponse().getContentAsString();
        // 拦截器
        HandlerInterceptor[] interceptors = mvcResult.getInterceptors();
        for (HandlerInterceptor interceptor : interceptors) {
            log.info("系统拦截器，名称：{}", interceptor.getClass().getName());
        }

        Assert.assertEquals("请求错误", 200, status);
        Assert.assertEquals("返回数据错误", "\"hello world\"", responseString);
    }
}
