package com.catpp.springbootpro.service.impl;

import com.catpp.springbootpro.mapper.SysUserMapper;
import com.catpp.springbootpro.pojo.SysUser;
import com.catpp.springbootpro.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.catpp.springbootpro.service.impl
 *
 * @Author cat_pp
 * @Date 2018/10/8
 * @Description
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser selectOne(Integer id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysUser> selectByModel(SysUser sysUser) {
        List<SysUser> userList = sysUserMapper.select(sysUser);
        return userList;
    }
}
