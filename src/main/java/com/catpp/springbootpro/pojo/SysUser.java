package com.catpp.springbootpro.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "sys_user")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 3061170688594751675L;
    @Id
    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private Integer age;

    private Integer sex;

    private String job;

    @Column(name = "face_image")
    private String faceImage;

    private String province;

    private String city;

    private String district;

    private String address;

    @Column(name = "auth_salt")
    private String authSalt;

    @Column(name = "last_login_ip")
    private String lastLoginIp;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "regist_time")
    private Date registTime;

}