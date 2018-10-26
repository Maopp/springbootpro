package com.catpp.springbootpro.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * com.catpp.springbootpro.utils
 *
 * @Author cat_pp
 * @Date 2018/10/26
 * @Description 获取spring上下文对象
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware{

    /**
     * 上下文对象实例
     */
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * 根据name获取Bean
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return context.getBean(name);
    }

    /**
     * 根据class获取Bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    /**
     * 根据name和class获取指定Bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return context.getBean(name, clazz);
    }

    /**
     * 根据注解class获取Beans
     * @param clazz
     * @return
     */
    public static Map<String, Object> getBeansWithAnnotation(Class clazz) {
        return context.getBeansWithAnnotation(clazz);
    }

    /**
     * 获取ClassLoader
     * @return
     */
    public static ClassLoader getClassloader() {
        return context.getClassLoader();
    }

    /**
     * 获取当前环境
     * @return
     */
    public static Environment getEnvironment() {
        return context.getEnvironment();
    }
    /**
     * 获取BeanFactory
     * @return
     */
    public static BeanFactory getBeanFactory() {
        return context.getParentBeanFactory();
    }

    /**
     * 根据name获取Resouce
     * @param name
     * @return
     * @throws IOException
     */
    public static Resource getResource(String name) throws IOException {
        return context.getResource(name);
    }

    /**
     * 根据name获取Resouces
     * @param name
     * @return
     * @throws IOException
     */
    public static Resource[] getResources(String name) throws IOException {
        return context.getResources(name);
    }

    /**
     * 根据name获取type
     * @param name
     * @return
     */
    public static Class getType(String name) {
        return context.getType(name);
    }
}
