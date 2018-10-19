package com.catpp.springbootpro.service.impl;

import com.catpp.springbootpro.mapper.SysUserMapper;
import com.catpp.springbootpro.pojo.SysUser;
import com.catpp.springbootpro.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.catpp.springbootpro.service.impl
 *
 * @Author cat_pp
 * @Date 2018/10/19
 * @Description
 */
@Service
// 开启声明参与缓存，如果@Cacheable不配置key，使用cacheNames的值作+方法名为key
@CacheConfig(cacheNames = "springbootpro:user")
public class CacheUserServiceImpl {

    @Autowired
    private SysUserMapper userMapper;

    @Cacheable
    public List<SysUser> selectAll() {
        return userMapper.selectAll();
    }
}
