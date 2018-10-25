package com.catpp.springbootpro.event.publish;

import com.catpp.springbootpro.pojo.SysUser;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * com.catpp.springbootpro.event.publish
 *
 * @Author cat_pp
 * @Date 2018/10/25
 * @Description
 */
@Data
public class UserRegisterEvent extends ApplicationEvent {

    /**
     * 注册用户对象
     */
    private SysUser user;

    /**
     * 重写构造函数
     * @param source 发生事件的对象
     * @param user
     */
    public UserRegisterEvent(Object source, SysUser user) {
        super(source);
        this.user = user;
    }
}
