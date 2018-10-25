package com.catpp.springbootpro.event.monitor;

import com.catpp.springbootpro.event.publish.UserRegisterEvent;
import com.catpp.springbootpro.pojo.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * com.catpp.springbootpro.event.monitor
 *
 * @Author cat_pp
 * @Date 2018/10/25
 * @Description 实现ApplicationListener接口实现监听，泛型参数传入注册的事件
 */
@Slf4j
@Component
public class RegisterListener implements ApplicationListener<UserRegisterEvent> {

    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        // 获取用户对象
        SysUser user = event.getUser();
        // 省略处理逻辑
        // 输出注册用户对象信息
        log.info("实现ApplicationListener接口完成事件监听，注册对象：{}", user.getUsername());
    }
}
