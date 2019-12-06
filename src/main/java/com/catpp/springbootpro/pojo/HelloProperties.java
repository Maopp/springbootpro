package com.catpp.springbootpro.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * com.catpp.springbootpro.pojo
 *
 * @Author cat_pp
 * @Date 2019/12/6
 * @Description
 */
@Data
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {

    private String msg = "test";
    private boolean show = false;
}
