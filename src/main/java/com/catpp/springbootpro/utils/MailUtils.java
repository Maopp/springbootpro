package com.catpp.springbootpro.utils;

import com.catpp.springbootpro.enums.MailContentTypeEnum;
import com.catpp.springbootpro.pojo.SysUser;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * com.catpp.springbootpro.utils
 *
 * @Author cat_pp
 * @Date 2018/10/15
 * @Description
 */
@Slf4j
public class MailUtils {

    /**
     * 发送邮件
     * @param title 主题
     * @param content 内容
     * @param contentType 内容格式
     * @param fromMailAddress 发送邮件地址
     * @param to 接收邮件地址集合
     * @param cc 抄送地址集合
     * @param bcc 密送地址集合
     * @param fileArr 附件集合（文件路径集合）
     */
    public static void send(String title, Object content, String contentType, String fromMailAddress, String to,
                            String cc, String bcc, String[] fileArr) {
        if (StringUtils.isEmpty(fromMailAddress)) {
            fromMailAddress = PropertiesReader.getValue("mail", "mail.from.address");
        }
        log.info("调用发送邮件接口，发送地址为：{}， 接收地址为：{}", fromMailAddress, to);
        /*Preconditions.checkArgument(StringUtils.isNotEmpty(fromMailAddress), "系统异常，请联系管理员");*/
        Preconditions.checkArgument(StringUtils.isNotEmpty(title), "邮件主题不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(contentType), "内容格式不能为空");
        Preconditions.checkArgument(null != content, "邮件内容不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(to), "收件人不能为空");
        // 从配置文件获取发件人邮箱地址
        /*Properties properties = PropertiesReader.getProperties("mail");*/
        // 使用配置文件记录邮箱一些属性信息
        Properties prop = new Properties();
        // 使用smtp发送邮件，进行身份验证
        prop.put("mail.smtp.auth", "true");
        // 服务器
        prop.put("mail.smtp.host", PropertiesReader.getValue("mail", "mail.smtp.service"));
        // 端口号：qq邮箱两个端口号（465/587）
        prop.put("mail.smtp.port", PropertiesReader.getValue("mail", "mail.smtp.port"));
        // 发件人
        prop.put("mail.user", fromMailAddress);
        // 发件邮箱的16位SMTP指令
        prop.put("mail.password", PropertiesReader.getValue("mail", "mail.from.smtp.pwd"));

        // 构建授权信息，用于SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                String userName = prop.getProperty("mail.user");
                String password = prop.getProperty("mail.password");
                return new javax.mail.PasswordAuthentication(userName, password);
            }
        };

        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(prop, authenticator);

        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        try {
            String nickName = MimeUtility.encodeText(PropertiesReader.getValue("mail", "mail.from.nickname"));
            InternetAddress from = new InternetAddress(nickName + "<" + prop.getProperty("mail.user") + ">");
            message.setFrom(from);
            // 设置收件人
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            // 设置抄送人
            if (StringUtils.isNotEmpty(cc)) {
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            }
            // 设置密送人
            if (StringUtils.isNotEmpty(bcc)) {
                message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
            }
            // 设置邮件标题
            message.setSubject(title);

            Multipart mp = new MimeMultipart();
            MimeBodyPart mbpContent = new MimeBodyPart();
            // 设置邮件内容
            /*if (StringUtils.equals(contentType, MailContentTypeEnum.HTML.getValue())) {
                message.setContent(content, contentType);
            } else if (StringUtils.equals(contentType, MailContentTypeEnum.TEXT.getValue())) {
                message.setText(content.toString());
            }*/
            if (StringUtils.equals(contentType, MailContentTypeEnum.HTML.getValue())) {
                mbpContent.setContent(content, contentType);
            } else if (StringUtils.equals(contentType, MailContentTypeEnum.TEXT.getValue())) {
                mbpContent.setText(content.toString());
            }
            mp.addBodyPart(mbpContent);

            // 设置附件内容
            if (null != fileArr && fileArr.length > 0) {
                for (String fileName : fileArr) {
                    MimeBodyPart mbpFile = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(fileName);
                    mbpFile.setDataHandler(new DataHandler(fds));
                    mbpFile.setFileName(toChinese(fds.getName()));
                    mp.addBodyPart(mbpFile);
                    log.info("添加附件成功，附件名称：{}", fds.getName());
                }
            }
            message.setContent(mp);
            message.setSentDate(new Date());
            // 发送邮件
            Transport.send(message);
            // 设置密送人
        } catch (UnsupportedEncodingException e) {
            log.error("转码失败，转码数据为：{}；错误信息：{}", PropertiesReader.getValue("mail", "mail.from.nickname"), e.getMessage());
            throw new RuntimeException("系统异常");
        } catch (AddressException e) {
            log.error("封装发件人信息失败，错误信息：{}", e.getMessage());
            throw new RuntimeException("系统异常");
        } catch (MessagingException e) {
            log.error("设置发件人信息失败，错误信息：{}", e.getMessage());
            throw new RuntimeException("系统异常");
        }
    }

    /**
     * 修改字符串编码
     * @param str
     * @return
     */
    private static String toChinese(String str) {
        try {
            str = MimeUtility.encodeText(new String(str.getBytes(), "GB2312"), "GB2312", "B");
        } catch (UnsupportedEncodingException e) {
            log.error("文件名称编码失败，错误信息：{}", e.getMessage());
        }
        return str;
    }

    /**
     * 发送邮件，多个收件人，不含附件
     * @param title 邮件标题
     * @param content 邮件内容
     * @param receiveList 收件人列表
     */
    public static void send(String title, Object content, List<String> receiveList) {
        Preconditions.checkArgument(!CollectionUtils.isEmpty(receiveList), "收件人不能为空");
        String to = jsonToString(receiveList);
        send(title, content, MailContentTypeEnum.HTML.getValue(), null, to, null, null, null);
    }

    /**
     * List集合转换为字符串
     * @param list
     */
    private static String jsonToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < list.size();i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /**
     * 发送邮件，一个收件人，不含附件
     * @param title 邮件主题
     * @param content 邮件内容
     * @param receive 收件人
     */
    public static void send(String title, Object content, String receive) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(receive), "收件人地址不能为空");
        send(title, content, MailContentTypeEnum.HTML.getValue(), null, receive, null, null, null);
    }

    /**
     * 发送邮件，一个收件人，含附件
     * @param title 邮件主题
     * @param content 邮件内容
     * @param receive 收件人
     * @param fileArr 附件集合
     */
    public static void send(String title, Object content, String receive, String[] fileArr) {
        send(title, content, MailContentTypeEnum.HTML.getValue(), null, receive, null, null, fileArr);
    }

    public static void main(String[] args) {
        String[] fileArr = new String[1];
        String filePath = "F:/test.txt";
        fileArr[0] = filePath;
        send("test_title", "test_content", "1121266440@qq.com", fileArr);
        System.out.println("发送成功");
    }
}
