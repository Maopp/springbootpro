package com.catpp.rabbitmqprovider.service;

import com.catpp.rabbitmqprovider.pojo.SysUser;

/**
 * com.catpp.rabbitmqprovider.service
 *
 * @Author cat_pp
 * @Date 2018/11/5
 * @Description
 */
public interface SysUserService {

    /**
     * 保存用户，写入消息队列
     *
     * @param sysUser
     * @throws Exception
     */
    Integer save(SysUser sysUser) throws Exception;
}
