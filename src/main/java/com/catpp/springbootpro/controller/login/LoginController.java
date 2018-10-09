package com.catpp.springbootpro.controller.login;

import com.catpp.springbootpro.common.JsonResult;
import com.catpp.springbootpro.pojo.SysUser;
import com.catpp.springbootpro.service.SysUserService;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * com.catpp.springbootpro.controller.login
 *
 * @Author cat_pp
 * @Date 2018/10/9
 * @Description 登陆
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private SysUserService userService;

    @RequestMapping("/login")
    public JsonResult login(SysUser sysUser, HttpServletRequest request) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(sysUser.getUsername()), "用户名不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(sysUser.getPassword()), "密码不能为空");

        List<SysUser> sysUsers = userService.selectByModel(sysUser);
        if (null != sysUsers && sysUsers.size() > 0) {
            // 将用户信息放入session
            HttpSession session = request.getSession();
            session.setAttribute("user", sysUsers.get(0));
            return JsonResult.ok("登陆成功");
        } else {
            SysUser user = new SysUser();
            user.setUsername(sysUser.getUsername());
            List<SysUser> users = userService.selectByModel(user);
            if (null != users && users.size() > 0) {
                return JsonResult.errorException("密码不正确");
            } else {
                return JsonResult.errorException("用户不存在");
            }
        }
    }
}
