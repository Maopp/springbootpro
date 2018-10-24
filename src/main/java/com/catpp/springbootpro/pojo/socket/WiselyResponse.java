package com.catpp.springbootpro.pojo.socket;

import lombok.Data;

/**
 * com.catpp.springbootpro.pojo.socket
 *
 * @Author cat_pp
 * @Date 2018/10/24
 * @Description 服务器向浏览器发返回消息
 */
@Data
public class WiselyResponse {

    private String responseMessage;

    public WiselyResponse() {
    }

    public WiselyResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
