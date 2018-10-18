package com.catpp.springbootpro.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.catpp.springbootpro.controller
 *
 * @Author cat_pp
 * @Date 2018/10/18
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class LogBackController {

    @RequestMapping("/testLog")
    public void testLog() {
        log.info("info级别日志");
        log.error("error级别日志");
        log.debug("debug级别日志");
        log.warn("warn级别日志");
    }
}
