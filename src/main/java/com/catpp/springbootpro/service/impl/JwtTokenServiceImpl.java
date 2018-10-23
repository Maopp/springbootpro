package com.catpp.springbootpro.service.impl;

import com.catpp.springbootpro.mapper.JwtTokenMapper;
import com.catpp.springbootpro.pojo.JwtToken;
import com.catpp.springbootpro.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * com.catpp.springbootpro.service.impl
 *
 * @Author cat_pp
 * @Date 2018/10/22
 * @Description
 */
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Autowired
    private JwtTokenMapper tokenMapper;

    @Override
    public JwtToken selectOneByAppId(String appId) {
        JwtToken token = new JwtToken();
        token.setAppId(appId);
        List<JwtToken> tokens = tokenMapper.select(token);
        if (!CollectionUtils.isEmpty(tokens)) {
            return tokens.get(0);
        }
        return null;
    }

    @Override
    public void save(JwtToken token) {
        tokenMapper.insert(token);
    }

    @Override
    public void update(JwtToken token) {
        tokenMapper.updateByPrimaryKey(token);
    }
}
