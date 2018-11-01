package com.catpp.springbootpro.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * com.catpp.springbootpro.utils
 *
 * @Author cat_pp
 * @Date 2018/10/30
 * @Description 3DES解密工具
 */
@Slf4j
public class DES3Util {

    /**
     * 一定要24位
     */
    public static final String DESKEY = "DE76E3EC39801CEEE0440025";

    private DES3Util() {

    }

    public synchronized static String encrypt(String content) {
        try {
            return encrypt(content, DESKEY, "UTF-8");
        } catch (Exception e) {
            log.error("加密失败，内容：{}", content);
        }
        return content;
    }

    /**
     * 加密内容
     * @param content 待加密内容
     * @param deskey 3DES key
     * @param charset 字符集
     * @return
     * @throws Exception
     */
    private static String encrypt(String content, String deskey, String charset) throws Exception {
        try {
            if (StringUtils.isEmpty(deskey) || deskey.length() != 24) {
                log.error("3des key`s length must be 24");
                throw new Exception("3des key`s length must be 24");
            }
            if (StringUtils.isEmpty(content)) {
                return "";
            }
            SecretKeySpec key = new SecretKeySpec(deskey.getBytes(charset), "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptBytes = cipher.doFinal(content.getBytes(charset));
            return new BASE64Encoder().encode(encryptBytes);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String decrypt(String content) {
        try {
            return decrypt(content, DESKEY, "UTF-8");
        } catch (Exception e) {
            log.error("解密失败，内容：{}", content);
            return null;
        }
    }

    /**
     * 解密内容
     * @param content 待解密内容
     * @param deskey 3DES key
     * @param charset 字符集
     * @return
     * @throws Exception
     */
    private static String decrypt(String content, String deskey, String charset) throws Exception {
        try {
            if (StringUtils.isEmpty(deskey) || deskey.length() != 24) {
                log.error("3des key`s length must be 24");
                throw new Exception("3des key`s length must be 24");
            }
            if (StringUtils.isEmpty(content)) {
                return "";
            }
            byte[] decryptBytes = null;
            decryptBytes = new BASE64Decoder().decodeBuffer(content);
            log.info("decryptBytes：{}", decryptBytes);
            SecretKeySpec key = new SecretKeySpec(deskey.getBytes(charset), "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(decryptBytes), charset);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * SHA-1加密
     * @param decript
     * @return
     */
    public static String SHA1(String decript) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte[] messageDigest = digest.digest();
            // create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为十六进制数
            for (int i = 0;i < messageDigest.length;i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("SHA-1加密失败，加密数据：{}", decript);
            e.printStackTrace();
        }
        return "";
    }
}
