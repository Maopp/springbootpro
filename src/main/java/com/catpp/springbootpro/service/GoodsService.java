package com.catpp.springbootpro.service;

import com.catpp.springbootpro.pojo.GoodInfo;

/**
 * com.catpp.springbootpro.service
 *
 * @Author cat_pp
 * @Date 2018/11/1
 * @Description
 */
public interface GoodsService {

    Integer save(GoodInfo goodInfo) throws Exception;
}
