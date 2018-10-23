package com.catpp.springbootpro.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "jwt_user")
public class JwtUser {
    @Id
    @Column(name = "app_id")
    private String appId;

    private String status;

    @Column(name = "day_request_count")
    private Integer dayRequestCount;

    @Column(name = "ajax_bind_ip")
    private String ajaxBindIp;

    private String mark;

    @Column(name = "app_secret")
    private byte[] appSecret;

}