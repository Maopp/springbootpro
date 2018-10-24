package com.catpp.springbootpro.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "logger_infos")
public class LoggerInfos {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 客户端请求ip
     */
    @Column(name = "cilent_ip")
    private String cilentIp;

    /**
     * 客服端请求路径
     */
    private String uri;

    /**
     * 终端请求方式、普通请求、ajax请求
     */
    private String type;

    /**
     * 请求方式method、post、get等
     */
    private String method;

    /**
     * 请求接口唯一session标识
     */
    @Column(name = "session_id")
    private String sessionId;

    /**
     * 请求时间
     */
    private Date time;

    /**
     * 接口返回时间
     */
    @Column(name = "return_time")
    private String returnTime;

    /**
     * 响应状态
     */
    @Column(name = "http_status_code")
    private String httpStatusCode;

    /**
     * 时间消费
     */
    @Column(name = "time_consuming")
    private Integer timeConsuming;

    /**
     * 请求参数内容，json
     */
    @Column(name = "param_data")
    private String paramData;

    /**
     * 接口返回数据json
     */
    @Column(name = "return_data")
    private String returnData;

    /**
     * 异常信息
     */
    @Column(name = "exception_info")
    private String exceptionInfo;
}