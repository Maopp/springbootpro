package com.catpp.springbootpro.controller.socket;

import com.catpp.springbootpro.pojo.socket.WiselyMessaage;
import com.catpp.springbootpro.pojo.socket.WiselyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * com.catpp.springbootpro.controller.socket
 *
 * @Author cat_pp
 * @Date 2018/10/24
 * @Description wscontroller
 */
@Slf4j
@Controller
public class WebSocketController {

    /**
     * 返回消息给浏览器
     * 使用MessageMapping注解开启WebSocket访问地址映射
     *
     * 当浏览器向服务端发送请求时，通过@MessageMapping映射/welcome这个地址，类
     * 似@RequestMapping，当服务端有消息存在时，会对订阅@SendTo中路径的浏览器发送请求
     *
     * @param messaage
     * @return
     * @throws InterruptedException
     */
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public WiselyResponse responseToClient(WiselyMessaage messaage) throws InterruptedException {
        // 等待2秒返回内容
        Thread.sleep(2000);
        log.info("使用socket进行通信，请求数据：{}", messaage.getName());
        return new WiselyResponse("欢迎使用WebSocket：" + messaage.getName());
    }

    /**
     * 定位到socket页面
     * 可以使用webmvc配置直接访问jsp页面：WebConfiguration.class
     *
     * @return
     */
    @RequestMapping("toWelcome")
    public String toWelcome() {
        return "/socket/welcome";
    }
}
