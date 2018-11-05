package com.catpp.rabbitmqprovider.mapper;

import com.catpp.rabbitmqprovider.pojo.SysUser;
import com.catpp.rabbitmqprovider.util.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends MyMapper<SysUser> {

    int save(@Param("model") SysUser sysUser);
}