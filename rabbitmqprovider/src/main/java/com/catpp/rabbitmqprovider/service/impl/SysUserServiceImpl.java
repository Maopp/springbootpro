package com.catpp.rabbitmqprovider.service.impl;

import com.catpp.rabbitmqcommon.enums.ExchangeEnum;
import com.catpp.rabbitmqcommon.enums.QueueEnum;
import com.catpp.rabbitmqprovider.mapper.SysUserMapper;
import com.catpp.rabbitmqprovider.pojo.SysUser;
import com.catpp.rabbitmqprovider.rabbitmq.QueueMessageService;
import com.catpp.rabbitmqprovider.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * com.catpp.rabbitmqprovider.service.impl
 *
 * @Author cat_pp
 * @Date 2018/11/5
 * @Description
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private QueueMessageService queueMessageService;

    @Override
    public Integer save(SysUser sysUser) throws Exception {
        userMapper.save(sysUser);
        queueMessageService.send(sysUser.getId(), ExchangeEnum.USER_REGISTER, QueueEnum.USER_REGISTER);
        return sysUser.getId();
    }
}
