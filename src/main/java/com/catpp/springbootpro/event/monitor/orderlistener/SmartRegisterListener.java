package com.catpp.springbootpro.event.monitor.orderlistener;

import com.catpp.springbootpro.event.publish.UserRegisterEvent;
import com.catpp.springbootpro.pojo.SysUser;
import com.catpp.springbootpro.service.impl.SysUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
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
public class SmartRegisterListener implements SmartApplicationListener {

    /**
     * 该方法返回true，同时supportsSourceType方法返回true，才会调用该监听内的onApplicationEvent方法
     * @param aClass 接收到的监听事件类型
     * @return
     */
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        // 只有事件类型是UserRegisterEvent才执行
        return aClass == UserRegisterEvent.class;
    }

    /**
     * 该方法返回true，同时supportsEventType方法返回true，才会调用该监听内的onApplicationEvent方法
     * @param aClass 接收到的数据源对象类型
     * @return
     */
    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        // 只有事件的数据源对象是SysUserServiceImpl时才执行
        return aClass == SysUserServiceImpl.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        UserRegisterEvent userRegisterEvent = (UserRegisterEvent) applicationEvent;
        // 获取用户对象
        SysUser user = userRegisterEvent.getUser();
        // 省略处理逻辑
        // 输出注册用户对象信息
        log.info("有序事件监听，注册对象：{}", user.getUsername());
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
