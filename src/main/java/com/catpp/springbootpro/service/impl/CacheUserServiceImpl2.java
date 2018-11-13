package com.catpp.springbootpro.service.impl;

import com.catpp.springbootpro.mapper.SysUserMapper;
import com.catpp.springbootpro.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.catpp.springbootpro.service.impl
 *
 * @Author cat_pp
 * @Date 2018/11/13
 * @Description springboot2.0 集成redis
 */
@Service
public class CacheUserServiceImpl2 {

    @Autowired
    private SysUserMapper userMapper;

    @Cacheable(cacheNames = "spring2.0.service.user")
    public List<SysUser> getAll() {
        return userMapper.selectAll();
    }
}
