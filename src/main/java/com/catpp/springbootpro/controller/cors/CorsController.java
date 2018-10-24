package com.catpp.springbootpro.controller.cors;

import com.catpp.springbootpro.common.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.catpp.springbootpro.controller.cors
 *
 * @Author cat_pp
 * @Date 2018/10/24
 * @Description 跨域请求测试类
 */
@RestController
public class CorsController {

    @RequestMapping("/cors")
    public JsonResult cors() {
        return JsonResult.ok("跨域请求成功");
    }
}
