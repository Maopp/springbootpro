package com.catpp.rabbitmq.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * com.catpp.rabbitmq.common.entity
 *
 * @Author cat_pp
 * @Date 2018/11/12
 * @Description 消息实体
 */
@Data
public class MessageEntity implements Serializable {

    private String content;
}
