package com.catpp.springbootpro.annotation.security.constants;

/**
 * com.catpp.springbootpro.annotation.security.constants
 *
 * @Author cat_pp
 * @Date 2018/10/30
 * @Description 加密内容常量配置，如DES请求参数key
 */
public interface ContentSecurityConstants {

    /**
     * 加密内容字符集
     */
    public static final String CONTENT_CHARSET = "UTF-8";

    /**
     * DES方式请求的request key
     */
    public static final String DES_PARAMETER_NAME = "desString";

    /**
     * DES解密key
     */
    public static final String DES_KEY = "DE76E3EC39801CEEE0440025";

    /**
     * AES方式请求的request key
     */
    public static final String AES_PARAMETER_NAME = "aesString";

    /**
     * AES解密key
     */
    public static final String AES_KEY = "catpp_springboot";

    /**
     * 传递的attribute前缀
     */
    public static final String ATTRIBUTE_PREFIX = "security_";
}
