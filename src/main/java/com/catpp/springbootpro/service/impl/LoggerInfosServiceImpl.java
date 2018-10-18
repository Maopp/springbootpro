package com.catpp.springbootpro.service.impl;

import com.catpp.springbootpro.mapper.LoggerInfosMapper;
import com.catpp.springbootpro.pojo.LoggerInfos;
import com.catpp.springbootpro.service.LoggerInfosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * com.catpp.springbootpro.service.impl
 *
 * @Author cat_pp
 * @Date 2018/10/17
 * @Description
 */
@Service
public class LoggerInfosServiceImpl implements LoggerInfosService {

    @Autowired
    private LoggerInfosMapper loggerInfosMapper;
    @Override
    public void save(LoggerInfos loggerInfos) {
        loggerInfosMapper.insert(loggerInfos);
    }
}
