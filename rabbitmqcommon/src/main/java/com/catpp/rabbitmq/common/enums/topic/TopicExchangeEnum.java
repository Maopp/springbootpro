package com.catpp.rabbitmq.common.enums.topic;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * com.catpp.rabbitmq.common.enums
 *
 * @Author cat_pp
 * @Date 2018/11/7
 * @Description topic交换配置枚举类
 */
@Getter
@AllArgsConstructor
public enum TopicExchangeEnum {

    USER_REGISTER("user.register.topic.exchange");

    private String name;
}
