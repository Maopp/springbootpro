package com.catpp.rabbitmq.common.enums.direct;

/**
 * com.catpp.rabbitmqcommon.enums
 *
 * @Author cat_pp
 * @Date 2018/11/5
 * @Description 队列配置枚举
 *
 * 存放队列信息和队列的路由配置信息
 */
public enum QueueEnum {

    /**
     * 用户注册枚举
     */
    USER_REGISTER("user.register.queue", "user.register");

    /**
     * 队列名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    /**
     * 队列路由键
     */
    private String routingKey;

    QueueEnum(String name, String routingKey) {
        this.name = name;
        this.routingKey = routingKey;
    }
}
