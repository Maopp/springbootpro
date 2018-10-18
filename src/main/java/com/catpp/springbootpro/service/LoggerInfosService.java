package com.catpp.springbootpro.service;

import com.catpp.springbootpro.pojo.LoggerInfos;

/**
 * com.catpp.springbootpro.service
 *
 * @Author cat_pp
 * @Date 2018/10/17
 * @Description
 */
public interface LoggerInfosService {

    /**
     * 保存日志信息
     * @param loggerInfos
     */
    void save(LoggerInfos loggerInfos);
}
