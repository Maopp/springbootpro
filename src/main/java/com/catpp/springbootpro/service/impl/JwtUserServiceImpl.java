package com.catpp.springbootpro.service.impl;

import com.catpp.springbootpro.mapper.JwtUserMapper;
import com.catpp.springbootpro.pojo.JwtUser;
import com.catpp.springbootpro.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * com.catpp.springbootpro.service.impl
 *
 * @Author cat_pp
 * @Date 2018/10/22
 * @Description
 */
@Service
public class JwtUserServiceImpl implements JwtUserService {

    @Autowired
    private JwtUserMapper userMapper;
    @Override
    public JwtUser selectOne(String appId) {
        return userMapper.selectByPrimaryKey(appId);
    }
}
