package com.catpp.springbootpro.function;

import com.catpp.springbootpro.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * com.catpp.springbootpro.function
 *
 * @Author cat_pp
 * @Date 2018/11/1
 * @Description
 */
@Slf4j
public class GoodsTimerTests extends BaseTest {

    // 模拟MVC测试对象
    private MockMvc mockMvc;

    // web项目上下文
    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * 所有测试方法执行之前执行该方法
     */
    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGoodsAdd() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/goods/save")
                .param("name", "商品名称1")
                .param("unit", "商品单元")
                .param("price", "1000"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        result.getResponse().setContentType("UTF-8");
        log.info(result.getResponse().getContentAsString());
    }
}
