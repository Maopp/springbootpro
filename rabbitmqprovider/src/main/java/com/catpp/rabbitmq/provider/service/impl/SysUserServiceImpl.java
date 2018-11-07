package com.catpp.rabbitmq.provider.service.impl;

import com.catpp.rabbitmq.common.enums.topic.TopicEnum;
import com.catpp.rabbitmq.common.enums.topic.TopicExchangeEnum;
import com.catpp.rabbitmq.provider.pojo.SysUser;
import com.catpp.rabbitmq.provider.rabbitmq.QueueMessageService;
import com.catpp.rabbitmq.common.enums.direct.DirectExchangeEnum;
import com.catpp.rabbitmq.common.enums.direct.QueueEnum;
import com.catpp.rabbitmq.provider.mapper.SysUserMapper;
import com.catpp.rabbitmq.provider.service.SysUserService;
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
        // 调用directExchange交换方式发送消息
        queueMessageService.send(sysUser.getId(), DirectExchangeEnum.USER_REGISTER, QueueEnum.USER_REGISTER);
        // 调用topicExchange交换方式发送消息
        queueMessageService.send(sysUser.getId(), TopicExchangeEnum.USER_REGISTER, TopicEnum.USER_REGISTER.getTopicRouteKey());
        return sysUser.getId();
    }
}
