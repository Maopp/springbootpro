package com.catpp.springbootpro.enums;

/**
 * com.catpp.springbootpro.enums
 *
 * @Author cat_pp
 * @Date 2018/10/16
 * @Description
 */
public enum MailContentTypeEnum {

    HTML("text/html;charset=UTF-8"),
    TEXT("text");

    private String value;

    MailContentTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
