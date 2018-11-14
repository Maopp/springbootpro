package com.catpp.springbootpro.pojo;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * com.catpp.springbootpro.pojo
 *
 * @Author cat_pp
 * @Date 2018/11/14
 * @Description
 */
@Data
public class Customer implements Serializable {

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * 通过@Id注解进行设置主键，不过这个主键的值是MongoDB自动生成的，生成的主键值是具有唯一性的。
     */
    @Id
    private String id;

    /**
     * 客户名称
     */
    private String firstName;

    /**
     * 客户姓氏
     */
    private String lastName;
}
