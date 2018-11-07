package com.catpp.rabbitmq.provider.mapper;

import com.catpp.rabbitmq.provider.pojo.SysUser;
import com.catpp.rabbitmq.provider.util.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends MyMapper<SysUser> {

    int save(@Param("model") SysUser sysUser);
}