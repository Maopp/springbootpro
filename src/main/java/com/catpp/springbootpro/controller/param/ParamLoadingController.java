package com.catpp.springbootpro.controller.param;

import com.catpp.springbootpro.annotation.paramresovle.ParameterModel;
import com.catpp.springbootpro.common.JsonResult;
import com.catpp.springbootpro.pojo.Student;
import com.catpp.springbootpro.pojo.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.catpp.springbootpro.controller.param
 *
 * @Author cat_pp
 * @Date 2018/10/30
 * @Description 自定义参数加载controller
 */
@Slf4j
@RestController
public class ParamLoadingController {

    @RequestMapping("/submit")
    public JsonResult resolver(@ParameterModel Teacher teacher, @ParameterModel Student student) {
        log.info(teacher.toString() + "---" + student.toString());
        return JsonResult.ok(teacher.getName() + student.getName());
    }
}
