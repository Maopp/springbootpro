package com.catpp.springbootpro.function;

import com.alibaba.fastjson.JSON;
import com.catpp.springbootpro.annotation.security.constants.ContentSecurityConstants;
import com.catpp.springbootpro.base.BaseTest;
import com.catpp.springbootpro.utils.DES3Util;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * com.catpp.springbootpro.function
 *
 * @Author cat_pp
 * @Date 2018/10/31
 * @Description
 */
@Slf4j
public class ContentSecurityTests extends BaseTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Before
    public void _init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testContentSecurity() throws Exception {
        // 参数列表
        Map<String, Object> params = new HashMap<>();
        params.put("name", "catpp");
        params.put("age", 20);
        // json转换字符串后进行加密
        String des = DES3Util.encrypt(JSON.toJSONString(params));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/contentSecurity")
                        .param(ContentSecurityConstants.DES_PARAMETER_NAME, des)
        ).andDo(MockMvcResultHandlers.print())
                .andDo(MockMvcResultHandlers.log())
                .andReturn();

        result.getResponse().setContentType("UTF-8");
        log.info(result.getResponse().getContentAsString());

        Assert.assertEquals("request failure", result.getResponse().getStatus(), 200);
        Assert.assertEquals("request failure", result.getResponse().getContentAsString(), "\"SUCCESS\"");
    }
}
