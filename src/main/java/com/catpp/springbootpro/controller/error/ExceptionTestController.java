package com.catpp.springbootpro.controller.error;

import com.catpp.springbootpro.common.JsonResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.catpp.springbootpro.controller.error
 *
 * @Author cat_pp
 * @Date 2018/10/24
 * @Description
 */
@RestController
@RequestMapping("/error")
public class ExceptionTestController {

    @RequestMapping("/test/{number}")
    public JsonResult test(@PathVariable int number) {
        int result = 20 / number;
        return JsonResult.ok(result);
    }
}
