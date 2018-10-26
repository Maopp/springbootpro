package com.catpp.springbootpro.controller.context;

import com.catpp.springbootpro.common.JsonResult;
import com.catpp.springbootpro.pojo.SysUser;
import com.catpp.springbootpro.service.impl.SysUserServiceImpl;
import com.catpp.springbootpro.utils.ApplicationContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * com.catpp.springbootpro.controller.context
 *
 * @Author cat_pp
 * @Date 2018/10/26
 * @Description
 */
@Controller
public class ContextController {

    @RequestMapping("/contextTest")
    @ResponseBody
    public JsonResult testApplicationContext() throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("getBeanByName", ApplicationContextUtils.getBean("sysUserServiceImpl"));
        map.put("getBeanByClass", ApplicationContextUtils.getBean(SysUserServiceImpl.class));
        map.put("getBeanByNameAndClass", ApplicationContextUtils.getBean("sysUserServiceImpl", SysUserServiceImpl.class));
        map.put("beanFactory", ApplicationContextUtils.getBeanFactory());
        map.put("getBeansWithAnnotation", ApplicationContextUtils.getBeansWithAnnotation(Controller.class));
        map.put("classLoader", ApplicationContextUtils.getClassloader());
        map.put("environment", ApplicationContextUtils.getEnvironment());
        map.put("resource", ApplicationContextUtils.getResource("sysUserServiceImpl"));
        map.put("resources", ApplicationContextUtils.getResources("sysUserServiceImpl"));
        map.put("type", ApplicationContextUtils.getType("sysUserServiceImpl"));
        map.put("getResourceAsStream", ApplicationContextUtils.getClassloader().getResourceAsStream("sysUserServiceImpl"));
        map.put("url", ApplicationContextUtils.getClassloader().getResource("sysUserServiceImpl"));
        return JsonResult.ok(map);
    }
}
