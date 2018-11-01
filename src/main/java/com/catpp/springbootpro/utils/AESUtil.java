package com.catpp.springbootpro.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * com.catpp.springbootpro.utils
 *
 * @Author cat_pp
 * @Date 2018/10/31
 * @Description
 */
@Slf4j
public class AESUtil {

    /**
     * 一定要16位
     */
    public static final String ENCRYPT_KEY = "catpp_springboot";

    /**
     * 加密方法
     * @param content 待加密内容
     * @return
     * @throws Exception
     */
    public static String encrypt(String content) throws Exception {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        return encrypt(content, ENCRYPT_KEY);
    }

    /**
     * 解密方法
     * @param content 待解密内容
     * @return
     * @throws Exception
     */
    public static String decrypt(String content) throws Exception {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        return decrypt(content, ENCRYPT_KEY);
    }

    /**
     * 解密方法
     * @param content 待解密内容
     * @param encryptKey 解密key
     * @return
     */
    private static String decrypt(String content, String encryptKey) throws Exception {
        // 判断加密串是否为空/是否为16位长度
        if (StringUtils.isEmpty(encryptKey) || 16 != encryptKey.length()) {
            log.error("加密key为空或者长度不是16位");
            return null;
        }
        byte[] keyBytes = encryptKey.getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        // 先用Base64解密
        byte[] decryptedTemp = new Base64().decode(content.getBytes());
        // 解密
        byte[] decrypted = cipher.doFinal(decryptedTemp);
        return new String(decrypted, "UTF-8");
    }

    /**
     * 加密方法
     * @param content 待加密内容
     * @param encryptKey 加密key
     * @return
     */
    private static String encrypt(String content, String encryptKey) throws Exception {
        // 判断加密串是否为空/是否为16位长度
        if (StringUtils.isEmpty(encryptKey) || 16 != encryptKey.length()) {
            log.error("加密key为空或者长度不是16位");
            return null;
        }
        // 对解密内容进行编码，转换为字节数组
        byte[] keyBytes = encryptKey.getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        // 算法/模式/补码方式，根据AES加密类型实例化加密算法对象
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryped = cipher.doFinal(content.getBytes("UTF-8"));
        // 使用BASE64做转码功能，同时能起到二次加密功能
        return new String(new Base64().encode(encryped), "UTF-8");
    }
}
