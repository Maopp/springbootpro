package com.catpp.rabbitmq.common.enums.direct;

/**
 * com.catpp.rabbitmqcommon.enums
 *
 * @Author cat_pp
 * @Date 2018/11/5
 * @Description rabbitmq交换枚举配置
 */
public enum DirectExchangeEnum {

    /**
     * 用户注册交换枚举
     */
    USER_REGISTER("user.register.direct.exchange");

    private String value;

    public String getValue() {
        return value;
    }

    DirectExchangeEnum(String value) {
        this.value = value;
    }
}
