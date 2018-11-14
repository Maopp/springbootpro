package com.catpp.springbootpro.function;

import com.alibaba.fastjson.JSON;
import com.catpp.springbootpro.base.BaseTest;
import com.catpp.springbootpro.mapper.CustomerRepository;
import com.catpp.springbootpro.pojo.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * com.catpp.springbootpro.function
 *
 * @Author cat_pp
 * @Date 2018/11/14
 * @Description
 */
@Slf4j
public class MongoTests extends BaseTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testMongo() {
        // 删除全部
        customerRepository.deleteAll();
        // 保存一条数据
        customerRepository.insert(new Customer("名字", "姓氏"));
        // 查询
        List<Customer> all = customerRepository.findAll();
        log.info(JSON.toJSONString(all));
    }
}
