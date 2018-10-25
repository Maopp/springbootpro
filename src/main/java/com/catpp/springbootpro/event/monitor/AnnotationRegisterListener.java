package com.catpp.springbootpro.event.monitor;

import com.catpp.springbootpro.event.publish.UserRegisterEvent;
import com.catpp.springbootpro.pojo.SysUser;
import com.catpp.springbootpro.utils.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * com.catpp.springbootpro.event.monitor
 *
 * @Author cat_pp
 * @Date 2018/10/25
 * @Description 使用注解方式实现事件监听
 */
@Slf4j
@Component
public class AnnotationRegisterListener {

    @EventListener
    public void register(UserRegisterEvent event) {
        // 获取注册用户对象
        SysUser user = event.getUser();
        // 省略处理逻辑
        // 输出注册用户信息
        log.info("@EventListener监听注册事件，注册对象：{}", user.getUsername());
    }

    /*@EventListener
    public void sendEmail(UserRegisterEvent event) {
        MailUtils.send("用户注册", event.getUser().getUsername(), "1121266440@qq.com");
        log.info("@EventListener监听注册事件，邮件发送成功");
    }*/
}
