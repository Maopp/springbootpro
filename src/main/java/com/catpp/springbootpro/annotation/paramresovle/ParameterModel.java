package com.catpp.springbootpro.annotation.paramresovle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.catpp.springbootpro.annotation.paramresovle
 *
 * @Author cat_pp
 * @Date 2018/10/29
 * @Description 参数实体映射注解
 *
 * 配置该注解的参数会使用com.catpp.springbootpro.annotation.paramresovle.CustomerArgumentResolver类来完成参数装载
 *
 * 该注解目前没有添加任何一个属性，这个也是可以根据项目的需求已经业务逻辑进行相应添加的，
 * 比如@RequestParam内常用的属性required、defaultValue等属性
 */
@Target(value = ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParameterModel {
}
