package com.catpp.springbootpro.annotation.paramresovle;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import java.lang.reflect.Field;
import java.util.*;

/**
 * com.catpp.springbootpro.annotation.paramresovle
 *
 * @Author cat_pp
 * @Date 2018/10/29
 * @Description 自定义参数装载
 */
@Slf4j
public class CustomerArgumentResolver implements HandlerMethodArgumentResolver{

    /**
     * 允许装载的参数，返回true时装载方法（resolveArgument）完成参数装载
     *
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        boolean flag = methodParameter.hasParameterAnnotation(ParameterModel.class);
        return flag;
    }

    /**
     * 装载参数，返回值会直接装载到指定参数上
     *
     * @param methodParameter 方法参数
     * @param modelAndViewContainer 返回视图容器
     * @param nativeWebRequest 本次请求对象
     * @param webDataBinderFactory 数据绑定工厂
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String parameterName = methodParameter.getParameterName();
        log.info("参数名称：{}", parameterName);

        // 目标返回对象
        // 如果model存在该Attribute时从model内获取并设置为返回值
        // 如果model不存在该Attribute则从request parameterMap内获取并设置为返回值
        Object target = modelAndViewContainer.containsAttribute(parameterName) ?
                modelAndViewContainer.getModel().get(parameterName) :
                createAttribute(parameterName, methodParameter, webDataBinderFactory, nativeWebRequest);
        return target;
    }

    /**
     * 根据请求参数属性名称获取请求值
     * @param parameterName 请求参数
     * @param methodParameter 参数对象
     * @param webDataBinderFactory 数据绑定对象
     * @param nativeWebRequest 请求对象
     * @return
     */
    private Object createAttribute(String parameterName, MethodParameter methodParameter,
                                   WebDataBinderFactory webDataBinderFactory, NativeWebRequest nativeWebRequest) throws Exception {
        // 获取属性值
        String value = getRequestValueForAttribute(parameterName, nativeWebRequest);
        if (StringUtils.isNotEmpty(value)) {
            // 检查请求类型和目标数据类型是否可以进行类型转换
            Object parameterValue = convertAttributeToParameterValue(value, parameterName, methodParameter,
                    webDataBinderFactory, nativeWebRequest);
            if (null != parameterValue) {
                return parameterValue;
            }
        } else {
            // 检查request ParameterMap中是否存在以parameterName为前缀的数据，如果有，根据字段的类型设置值/集合/数组等
            Object parameter = putParameters(methodParameter, nativeWebRequest);
            if (null != parameter) {
                return parameter;
            }
        }
        // 如果以上两种条件都不符合，返回初始化参数类型的空对象
        return BeanUtils.instantiateClass(methodParameter.getParameterType());
    }

    /**
     * 个性化定制
     *
     * 该方法实现了自定义规则xxx.xxx方式进行参数装载的逻辑，
     * 我们在前台传递参数的时候只需要将Controller内方法参数名称作为传递的前缀即可，如：teacher.name、student.name
     *
     * 从request中获取methodParameter前缀的所有参数，根据methodParameter的类型将对应字段的值设置到methodParameter对象内并返回
     * @param methodParameter
     * @param nativeWebRequest
     * @return
     */
    private Object putParameters(MethodParameter methodParameter, NativeWebRequest nativeWebRequest) {
        // 根据请求参数类型初始化空对象
        Object object = BeanUtils.instantiateClass(methodParameter.getParameterType());
        // 获取指定前缀的请求参数集合
        Map<String, String[]> parameters = getPrefixParameterMap(methodParameter.getParameterName(), nativeWebRequest, true);

        Iterator<String> iterator = parameters.keySet().iterator();
        while (iterator.hasNext()) {
            // 字段名称
            String fieldName = iterator.next();
            // 请求参数值
            String[] parameterValue = parameters.get(fieldName);

            try {
                Field field = object.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                // 字段类型
                Class<?> targetFieldType = field.getType();
                // List（ArrayList、LinkedList）类型，将List集合对象转换为数组类型的值
                if (List.class.isAssignableFrom(targetFieldType)) {
                    field.set(object, Arrays.asList(parameterValue));
                } else if (Object[].class.isAssignableFrom(targetFieldType)) {
                    // 数组类型，直接将数组值设置为目标对象的值
                    field.set(object, parameterValue);
                } else {
                    // 单值时获取索引为0的值
                    field.set(object, parameterValue[0]);
                }
            } catch (IllegalAccessException e) {
                log.error("Set Field Value Error，Field:{}, In {}", fieldName, object.getClass().getName());
                continue;
            } catch (NoSuchFieldException e) {
                log.error("Set Field Value Error，Field:{}, In {}", fieldName, object.getClass().getName());
                continue;
            }
        }
        return object;
    }

    /**
     * 获取指定前缀的参数：包括uri variables和parameters
     * @param parameterName
     * @param nativeWebRequest
     * @param subPrefix 是否截取掉parameterName的前缀
     * @return
     */
    private Map<String,String[]> getPrefixParameterMap(String parameterName, NativeWebRequest nativeWebRequest, boolean subPrefix) {
        Map<String, String[]> result = new HashMap<>();
        // 从PathVariables中获取该前缀的参数列表
        Map<String, String> variables = getUriTemplateVariables(nativeWebRequest);

        int prefixLength = parameterName.length();

        for (String name : variables.keySet()) {
            if (name.startsWith(parameterName)) {
                // 截取前缀
                if (subPrefix) {
                    char ch = name.charAt(prefixLength);
                    // 如果下一个字符不是数字/./_，则不是参数，只是前缀相似
                    if (illegalChar(ch)) {
                        continue;
                    }
                    result.put(name.substring(prefixLength + 1), new String[] {variables.get(name)});
                } else {
                    result.put(name, new String[]{variables.get(name)});
                }
            }
        }

        // 从request parameterMap集合内获取该前缀的参数列表
        Iterator<String> parameterNames = nativeWebRequest.getParameterNames();
        while (parameterNames.hasNext()) {
            String name = parameterNames.next();
            if (name.startsWith(parameterName)) {
                // 截取前缀
                if (subPrefix) {
                    char ch = 0;
                    try {
                        ch = name.charAt(prefixLength);
                    } catch (Exception e) {
                        log.error("获取参数的参数长度位置的字符出错，获取位置：{}；错误信息：{}", prefixLength, e.getMessage());
                        throw new RuntimeException("获取参数的参数长度位置的字符出错");
                    }
                    // 如果下一个字符不是数字/./_，则不是参数，只是前缀相似
                    if (illegalChar(ch)) {
                        continue;
                    }
                    result.put(name.substring(prefixLength + 1), nativeWebRequest.getParameterValues(name));
                } else {
                    result.put(name, nativeWebRequest.getParameterValues(name));
                }
            }
        }

        return result;
    }

    /**
     * 验证参数前缀是否合法
     * @param ch
     * @return
     */
    private boolean illegalChar(char ch) {
        return ch != '.' && ch != '_' && !(ch >= '0' && ch <= '9');
    }

    /**
     * 将parametername的值转换为methodParameter类型
     * @param value 请求值
     * @param parameterName 请求参数名称
     * @param methodParameter 目标参数对象
     * @param webDataBinderFactory 数据绑定工厂
     * @param nativeWebRequest 请求对象
     * @return
     */
    private Object convertAttributeToParameterValue(String value, String parameterName, MethodParameter methodParameter,
                                                    WebDataBinderFactory webDataBinderFactory, NativeWebRequest nativeWebRequest) throws Exception {
        // 获取类型转换业务逻辑实现类
        DataBinder binder = webDataBinderFactory.createBinder(nativeWebRequest, null, parameterName);
        ConversionService conversionService = binder.getConversionService();
        if (null != conversionService) {
            // 源类型描述
            TypeDescriptor source = TypeDescriptor.valueOf(String.class);
            // 根据目标参数对象获取目标参数类型
            TypeDescriptor target = new TypeDescriptor(methodParameter);
            // 验证是否可以进行转换
            if (conversionService.canConvert(source, target)) {
                // 返回转换后的值
                return binder.convertIfNecessary(value, methodParameter.getParameterType(), methodParameter);
            }
        }
        return null;
    }

    /**
     * 从request parameterMap获取请求参数的值
     * @param parameterName
     * @param nativeWebRequest
     * @return
     */
    private String getRequestValueForAttribute(String parameterName, NativeWebRequest nativeWebRequest) {
        // 获取PathVariables参数集合
        Map<String, String> variables = getUriTemplateVariables(nativeWebRequest);
        // 如果PathVariables集合中有该属性值，返回对应的值
        if (StringUtils.isNotEmpty(variables.get(parameterName))) {
            return variables.get(parameterName);
        } else if (StringUtils.isNotEmpty(nativeWebRequest.getParameter(parameterName))) {
            return nativeWebRequest.getParameter(parameterName);
        } else {
            return null;
        }
    }

    /**
     * 获取PathVariables集合
     * @param nativeWebRequest
     * @return
     */
    private Map<String,String> getUriTemplateVariables(NativeWebRequest nativeWebRequest) {
        Map<String, String> variables =
                (Map<String, String>) nativeWebRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE,
                        RequestAttributes.SCOPE_REQUEST);
        return variables;
    }
}
