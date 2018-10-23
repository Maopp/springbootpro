package com.catpp.springbootpro.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "jwt_token")
public class JwtToken {
    @Id
    private Integer id;

    @Column(name = "app_id")
    private String appId;

    @Column(name = "build_time")
    private String buildTime;

    private byte[] token;

}