package com.catpp.springbootpro.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * com.catpp.springbootpro.common
 *
 * @Author cat_pp
 * @Date 2018/10/15
 * @Description 邮件实体类
 */
public class MailEntity implements Serializable {
    private static final long serialVersionUID = 440507858888521395L;

    /**
     * smtp服务器
     */
    private String smtpService;
    /**
     * 端口号
     */
    private String smptPort;
    /**
     * 发送邮箱
     */
    private String fromMailAddress;
    /**
     * 发送邮箱的STMP口令
     */
    private String fromMailStmpPwd;
    /**
     * 邮件主题
     */
    private String title;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 内容格式（默认是html）
     */
    private String contentType;
    /**
     * 接收邮件地址集合
     */
    private List<String> receiveList = new ArrayList<>();

    public String getSmtpService() {
        return smtpService;
    }

    public void setSmtpService(String smtpService) {
        this.smtpService = smtpService;
    }

    public String getSmptPort() {
        return smptPort;
    }

    public void setSmptPort(String smptPort) {
        this.smptPort = smptPort;
    }

    public String getFromMailAddress() {
        return fromMailAddress;
    }

    public void setFromMailAddress(String fromMailAddress) {
        this.fromMailAddress = fromMailAddress;
    }

    public String getFromMailStmpPwd() {
        return fromMailStmpPwd;
    }

    public void setFromMailStmpPwd(String fromMailStmpPwd) {
        this.fromMailStmpPwd = fromMailStmpPwd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public List<String> getReceiveList() {
        return receiveList;
    }

    public void setReceiveList(List<String> receiveList) {
        this.receiveList = receiveList;
    }
}
