package com.catpp.springbootpro.annotation.security.content;

import com.catpp.springbootpro.annotation.security.base.BaseMethodArgumentResolver;
import com.catpp.springbootpro.annotation.security.constants.ContentSecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.BindException;
import java.util.Enumeration;
import java.util.Map;

/**
 * com.catpp.springbootpro.annotation.security.content
 *
 * @Author cat_pp
 * @Date 2018/10/30
 * @Description 自定义方法参数映射
 *
 * 实现了HandlerMethodArgumentResolver接口的方法：supportParameter 和 resolveArgument方法
 * 通过supportParameter方法判断只有存在ContentSecurityAttribute注解的参数才会被执行resolverArgument方法
 */
@Slf4j
public class ContentSecurityMethodArgumentResolver extends BaseMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(ContentSecurityAttribute.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        // 获取@ContentSecurityAttribute配置的value值，作为参数名称
        String name = methodParameter.getParameterAnnotation(ContentSecurityAttribute.class).value();

        // 获取值， 如果请求集合内存在则直接获取，如果不存在则调用createAttribute方法创建
        Object target = modelAndViewContainer.containsAttribute(name) ?
                modelAndViewContainer.getModel().get(name) : createAttribute(name, methodParameter, webDataBinderFactory, nativeWebRequest);

        // 创建参数绑定
        WebDataBinder binder = webDataBinderFactory.createBinder(nativeWebRequest, target, name);
        // 获取返回值实例
        target = binder.getTarget();
        // 如果存在返回值
        if (null != target) {
            // 设置返回值对象内的所有field的值，从request.getAttribute方法获取
            bindResquestAttributes(binder, nativeWebRequest);
            // 调用@Valid验证参数有效性
            validateIfApplicable(binder, methodParameter);
            // 存在参数绑定异常，抛出异常
            if (binder.getBindingResult().hasErrors()) {
                if (isBindExceptionRequired(binder, methodParameter)) {
                    throw new BindException(binder.getBindingResult().toString());
                }
            }
        }
        // 转换返回对象
        target = binder.convertIfNecessary(binder.getTarget(), methodParameter.getParameterType());
        return target;
    }

    private boolean isBindExceptionRequired(WebDataBinder binder, MethodParameter methodParameter) {
        int i = methodParameter.getParameterIndex();
        Class<?>[] parameterTypes = methodParameter.getMethod().getParameterTypes();
        boolean hasBindingResult = parameterTypes.length > (i + 1) && Errors.class.isAssignableFrom(parameterTypes[i + 1]);
        return !hasBindingResult;
    }

    private void validateIfApplicable(WebDataBinder binder, MethodParameter methodParameter) {
        Annotation[] parameterAnnotations = methodParameter.getParameterAnnotations();
        for (Annotation annotation : parameterAnnotations) {
            if (annotation.annotationType().getSimpleName().startsWith("Valid")) {
                Object hints = AnnotationUtils.getValue(annotation);
                binder.validate(hints instanceof Object[] ? (Object[]) hints : new Object[] {hints});
            }
        }
    }

    /**
     * 绑定请求参数
     * @param binder
     * @param nativeWebRequest
     */
    private void bindResquestAttributes(WebDataBinder binder, NativeWebRequest nativeWebRequest) throws Exception {
        // 获取返回对象实例
        Object obj = binder.getTarget();
        // 获取返回值类型
        Class<?> targetType = binder.getTarget().getClass();
        // 转换本地request对象为HttpServletRequest对象
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        // 获取所有attributes
        Enumeration<String> attributeNames = request.getAttributeNames();
        // 遍历设置值
        while (attributeNames.hasMoreElements()) {
            // 获取attribute name
            String attributeName = String.valueOf(attributeNames.nextElement());
            // 进处理ContentSecurityConstants.ATTRIBUTE_PREFIX开头的attribute
            if (!attributeName.startsWith(ContentSecurityConstants.ATTRIBUTE_PREFIX)) {
                continue;
            }
            // 获取字段名
            String fieldName = attributeName.replace(ContentSecurityConstants.ATTRIBUTE_PREFIX, "");
            Field field = null;
            try {
                field = targetType.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                // 如果返回对象类型内不存在字段，则从父类读取
                try {
                    field = targetType.getSuperclass().getDeclaredField(fieldName);
                } catch (NoSuchFieldException e1) {
                    log.debug("未查询到属性名称，属性名称：{}", fieldName);
                    continue;
                }
                // 如果父类还不存在，则直接跳出循环
                if (null == field) {
                    continue;
                }
            }
            // 设置字段的值
            field.setAccessible(true);
            String fieldClassName = field.getType().getSimpleName();
            Object attributeObj = request.getAttribute(attributeName);

            log.debug("映射安全字段：{}，字段类型：{}，字段内容：{}", fieldName, fieldClassName, attributeObj);

            if ("String".equals(fieldClassName)) {
                field.set(obj, attributeObj);
            } else if ("Integer".equals(fieldClassName)) {
                // field.setInt(obj, new Integer(attributeObj.toString()));
                field.set(obj, attributeObj);
            } else {
                field.set(obj, attributeObj);
            }
        }
        ServletRequestDataBinder servletRequestDataBinder = (ServletRequestDataBinder) binder;
        servletRequestDataBinder.bind(new MockHttpServletRequest());
    }

    private Object createAttribute(String name, MethodParameter methodParameter,
                                   WebDataBinderFactory webDataBinderFactory, NativeWebRequest nativeWebRequest) throws Exception {
        String value = getRequestValueForAttribute(name, nativeWebRequest);
        if (StringUtils.isNotEmpty(value)) {
            Object attribute = createAttributeFromRequestValue(value, name, methodParameter, webDataBinderFactory, nativeWebRequest);
            if (null != attribute) {
                return attribute;
            }
        }
        return BeanUtils.instantiateClass(methodParameter.getParameterType());
    }

    private Object createAttributeFromRequestValue(String value, String name, MethodParameter methodParameter,
                                                   WebDataBinderFactory webDataBinderFactory, NativeWebRequest nativeWebRequest) throws Exception {
        DataBinder binder = webDataBinderFactory.createBinder(nativeWebRequest, null, name);
        ConversionService conversionService = binder.getConversionService();
        if (null != conversionService) {
            TypeDescriptor source = TypeDescriptor.valueOf(String.class);
            TypeDescriptor target = new TypeDescriptor(methodParameter);
            if (conversionService.canConvert(source, target)) {
                return binder.convertIfNecessary(value, methodParameter.getParameterType(), methodParameter);
            }
        }
        return null;
    }

    private String getRequestValueForAttribute(String name, NativeWebRequest nativeWebRequest) {
        Map<String, String> variables = getUriTemplateVariables(nativeWebRequest);
        if (StringUtils.isNotEmpty(variables.get(name))) {
            return variables.get(name);
        } else if (StringUtils.isNotEmpty(nativeWebRequest.getParameter(name))) {
            return nativeWebRequest.getParameter(name);
        } else {
            return null;
        }
    }
}
