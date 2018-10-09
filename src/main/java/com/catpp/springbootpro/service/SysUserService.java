package com.catpp.springbootpro.service;

import com.catpp.springbootpro.pojo.SysUser;

/**
 * com.catpp.springbootpro.service
 *
 * @Author cat_pp
 * @Date 2018/10/8
 * @Description
 */
public interface SysUserService {

    SysUser selectOne(Integer id);
}
