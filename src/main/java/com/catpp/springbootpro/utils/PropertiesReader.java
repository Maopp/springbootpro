package com.catpp.springbootpro.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * com.catpp.springbootpro.utils
 *
 * @Author cat_pp
 * @Date 2018/10/16
 * @Description 资源文件读取类
 */
public class PropertiesReader {
    private static Map<String, Properties> filePropMapping = new WeakHashMap<>();

    public PropertiesReader() {
    }

    /**
     * 获取属性值
     * @param file_name 文件名
     * @param key 键值
     * @return
     * @throws MissingResourceException
     */
    public static String getValue(String file_name, String key) throws MissingResourceException {
        ResourceBundle res = ResourceBundle.getBundle(file_name);
        String value = res.getString(key);
        return value.trim();
    }

    /**
     * 读取最新属性值
     * @param file_name
     * @param key
     * @return
     */
    public static String getValueNoCache(String file_name, String key) {
        InputStream fileInputStream = null;
        String val;
        try{
            fileInputStream = PropertiesReader.class.getClassLoader().getResourceAsStream(file_name);
            Properties properties = new Properties();
            properties.load(fileInputStream);
            String value = properties.getProperty(key);
            val = value.trim();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
                throw new RuntimeException(e2);
            }
        }

        return val;
    }

    public static Properties fillProperties(String file_name) throws MissingResourceException {
        if (!file_name.endsWith(".properties")) {
            file_name = file_name + ".properties";
        }

        return getProperties(file_name);
    }

    public static Properties getProperties(String file_name) {
        return getProperties(file_name, PropertiesReader.class.getClassLoader());
    }

    private static Properties getProperties(String file_name, ClassLoader classLoader) {
        Properties properties = new Properties();
        if (filePropMapping.containsKey(file_name)) {
            properties = (Properties) filePropMapping.get(file_name);
        } else {
            InputStream inputStream = classLoader.getResourceAsStream(file_name);

            try {
                properties.load(inputStream);
                filePropMapping.put(file_name, properties);
            } catch (IOException e) {
                throw new RuntimeException("load properties file error", e);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    throw new RuntimeException("load properties file error", e2);
                }
            }
        }
        return properties;
    }

    public static void main(String[] args) {
        String val = getValue("mail", "mail.from.address");
        System.out.println(val);
        // todo 获取资源文件失败
        /*Properties mail = getProperties("mail");
        String property = mail.getProperty("mail.from.address");
        System.err.println(property);*/
    }
}
