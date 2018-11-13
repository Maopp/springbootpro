package com.catpp.springbootpro.function;

import com.catpp.springbootpro.base.BaseTest;
import com.catpp.springbootpro.service.impl.CacheUserServiceImpl2;
import org.databene.contiperf.PerfTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * com.catpp.springbootpro.function
 *
 * @Author cat_pp
 * @Date 2018/11/13
 * @Description
 */
public class RedisCacheTests extends BaseTest {

    @Autowired
    private CacheUserServiceImpl2 userService;

    /**
     * 添加测试将数据刷新到redis缓存
     */
    @Test
    public void testAll() {
        userService.getAll();
    }

    /**
     * 性能测试
     * 10万次查询，100个线程同时操作getAll()方法
     */
    @Test
    @PerfTest(invocations = 100000, threads = 100)
    public void testPerformance() {
        userService.getAll();
    }
}
