package com.catpp.rabbitmq.common.enums;

/**
 * com.catpp.rabbitmqcommon.enums
 *
 * @Author cat_pp
 * @Date 2018/11/5
 * @Description rabbitmq交换枚举配置
 */
public enum ExchangeEnum {

    /**
     * 用户注册交换枚举
     */
    USER_REGISTER("user.register.topic.exchange");

    private String value;

    public String getValue() {
        return value;
    }

    ExchangeEnum(String value) {
        this.value = value;
    }
}
