package com.catpp.springbootpro.controller;

import com.catpp.springbootpro.common.JsonResult;
import com.catpp.springbootpro.pojo.SysUser;
import com.catpp.springbootpro.service.SysUserService;
import com.catpp.springbootpro.service.impl.CacheUserServiceImpl;
import com.catpp.springbootpro.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * com.catpp.springbootpro.controller
 *
 * @Author cat_pp
 * @Date 2018/10/19
 * @Description
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private CacheUserServiceImpl userService;
    @RequestMapping("/all")
    public JsonResult list() {
        List<SysUser> all = userService.selectAll();
        return JsonResult.ok(all);
    }
}
