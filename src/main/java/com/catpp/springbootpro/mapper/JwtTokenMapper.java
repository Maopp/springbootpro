package com.catpp.springbootpro.mapper;

import com.catpp.springbootpro.pojo.JwtToken;
import com.catpp.springbootpro.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface JwtTokenMapper extends MyMapper<JwtToken> {
    JwtToken selectOneByAppId(@Param("appId") String appId);
}