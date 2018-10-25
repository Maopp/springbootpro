package com.catpp.springbootpro.event.monitor.orderlistener;

import com.catpp.springbootpro.event.publish.UserRegisterEvent;
import com.catpp.springbootpro.service.impl.SysUserServiceImpl;
import com.catpp.springbootpro.utils.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * com.catpp.springbootpro.event.monitor.orderlistener
 *
 * @Author cat_pp
 * @Date 2018/10/25
 * @Description
 */
@Slf4j
@Component
public class SmartEmailListener implements SmartApplicationListener {
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass == UserRegisterEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        return aClass == SysUserServiceImpl.class;
    }

    /**
     * supportsEventType和supportsSourceType全部返回true时才会执行
     * @param applicationEvent 监听事件类型对象
     */
    @Override
    @Async
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        // 睡眠3秒钟，验证异步调用
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            log.error("调用Thread的sleep方法出错，错误信息：{}", e.getMessage());
        }
        UserRegisterEvent userRegisterEvent = (UserRegisterEvent) applicationEvent;
        // MailUtils.send("用户注册", userRegisterEvent.getUser().getUsername(), "1121266440@qq.com");
        log.info("有序事件监听，邮件发送成功");
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
