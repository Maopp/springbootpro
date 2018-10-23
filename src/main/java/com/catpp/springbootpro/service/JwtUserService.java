package com.catpp.springbootpro.service;

import com.catpp.springbootpro.pojo.JwtUser;

/**
 * com.catpp.springbootpro.service
 *
 * @Author cat_pp
 * @Date 2018/10/22
 * @Description
 */
public interface JwtUserService {

    JwtUser selectOne(String appId);
}
