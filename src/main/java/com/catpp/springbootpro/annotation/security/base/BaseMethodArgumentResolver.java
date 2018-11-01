package com.catpp.springbootpro.annotation.security.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerMapping;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * com.catpp.springbootpro.annotation.security.base
 *
 * @Author cat_pp
 * @Date 2018/10/30
 * @Description 参数装载实现类，实现HandlerMethodArgumentResolver接口完成
 */
@Slf4j
public abstract class BaseMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 获取指定前缀的参数：包括uri variables和parameters
     * @param parameterName
     * @param nativeWebRequest
     * @param subPrefix 是否截取掉parameterName的前缀
     * @return
     */
    protected Map<String,String[]> getPrefixParameterMap(String parameterName, NativeWebRequest nativeWebRequest, boolean subPrefix) {
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
    protected boolean illegalChar(char ch) {
        return ch != '.' && ch != '_' && !(ch >= '0' && ch <= '9');
    }

    /**
     * 获取PathVariables集合
     * @param nativeWebRequest
     * @return
     */
    protected Map<String,String> getUriTemplateVariables(NativeWebRequest nativeWebRequest) {
        Map<String, String> variables =
                (Map<String, String>) nativeWebRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE,
                        RequestAttributes.SCOPE_REQUEST);
        return variables;
    }
}
