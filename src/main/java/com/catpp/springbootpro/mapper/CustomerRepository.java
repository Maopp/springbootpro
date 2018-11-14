package com.catpp.springbootpro.mapper;

import com.catpp.springbootpro.pojo.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * com.catpp.springbootpro.mapper
 *
 * @Author cat_pp
 * @Date 2018/11/14
 * @Description
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
