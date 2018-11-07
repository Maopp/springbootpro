package com.catpp.rabbitmq.common.enums.topic;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * com.catpp.rabbitmq.common.enums.topic
 *
 * @Author cat_pp
 * @Date 2018/11/7
 * @Description topic交换路由key配置枚举类
 */
@Getter
@AllArgsConstructor
public enum TopicEnum {

    /**
     * 用户注册topic路由key配置
     */
    USER_REGISTER("register.user");

    private String topicRouteKey;
}
