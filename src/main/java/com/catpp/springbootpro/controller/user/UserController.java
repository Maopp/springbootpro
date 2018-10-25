package com.catpp.springbootpro.controller.user;

import com.catpp.springbootpro.common.JsonResult;
import com.catpp.springbootpro.pojo.SysUser;
import com.catpp.springbootpro.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.catpp.springbootpro.controller.user
 *
 * @Author cat_pp
 * @Date 2018/10/25
 * @Description
 */
@RestController
public class UserController {

    @Autowired
    private SysUserService userService;

    @RequestMapping("/register")
    public JsonResult register(SysUser user) {
        user.setUsername("test_event_register");
        user.setPassword("123qwe");
        userService.register(user);
        return JsonResult.ok("注册成功");
    }
}
