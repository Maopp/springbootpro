package com.catpp.springbootpro.mapper;

import com.catpp.springbootpro.pojo.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * com.catpp.springbootpro.mapper
 *
 * @Author cat_pp
 * @Date 2018/11/14
 * @Description
 */
@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends MongoRepository<Customer, String> {

    /**
     * 自定义方法，可以使用{repository}/search/{param}访问
     *
     * @param firstName
     * @return
     */
    List<Customer> findByFirstName(@Param("firstName") String firstName);
}
