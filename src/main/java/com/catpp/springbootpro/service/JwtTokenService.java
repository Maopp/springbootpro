package com.catpp.springbootpro.service;

import com.catpp.springbootpro.mapper.JwtTokenMapper;
import com.catpp.springbootpro.pojo.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * com.catpp.springbootpro.service
 *
 * @Author cat_pp
 * @Date 2018/10/22
 * @Description
 */
public interface JwtTokenService {

    JwtToken selectOneByAppId(String appId);

    void save(JwtToken token);

    void update(JwtToken token);
}
