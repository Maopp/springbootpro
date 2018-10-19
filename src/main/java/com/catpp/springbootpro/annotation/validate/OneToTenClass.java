package com.catpp.springbootpro.annotation.validate;

import com.catpp.springbootpro.annotation.validate.OneToTen;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * com.catpp.springbootpro.annotation
 *
 * @Author cat_pp
 * @Date 2018/10/19
 * @Description
 */
public class OneToTenClass implements ConstraintValidator<OneToTen, Object> {

    /**
     * 临时变量保存参数值列表
     */
    private String values;

    /**
     * 初始化values的值
     * @param constraintAnnotation 注解对象
     */
    @Override
    public void initialize(OneToTen constraintAnnotation) {
        // 将注解内配置的值赋给临时变量
        this.values = constraintAnnotation.values();
    }

    /**
     * 实现验证
     * @param o
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        // 分割定义的参数值
        String[] valueArr = values.split(",");
        // 遍历比对参数值
        if (null == valueArr || valueArr.length <= 0) {
            return false;
        }
        for (String param : valueArr) {
            if (StringUtils.equals(param, o.toString())) {
                return true;
            }
        }
        return false;
    }
}
