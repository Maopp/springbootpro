package com.catpp.springbootpro.mapper;

import com.catpp.springbootpro.pojo.GoodInfo;
import com.catpp.springbootpro.utils.MyMapper;

public interface GoodInfoMapper extends MyMapper<GoodInfo> {
    int save(GoodInfo goodInfo);
}