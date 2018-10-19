package com.catpp.springbootpro.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * com.catpp.springbootpro.annotation
 *
 * @Author cat_pp
 * @Date 2018/10/19
 * @Description 自定义注解，只能取1到10之间的值
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = OneToTenClass.class)
public @interface OneToTen {

    // 多个有效值使用“，”隔开
    String values();
    // 提示内容
    String message() default "参数不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
