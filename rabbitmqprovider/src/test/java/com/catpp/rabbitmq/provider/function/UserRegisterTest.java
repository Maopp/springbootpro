package com.catpp.rabbitmq.provider.function;

import com.catpp.rabbitmq.provider.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * com.catpp.rabbitmqprovider.function
 *
 * @Author cat_pp
 * @Date 2018/11/6
 * @Description
 */
@Slf4j
public class UserRegisterTest extends BaseTest {

    @Test
    public void testBatchUserAdd() throws Exception {
        for (int i = 0;i < 10;i++) {
            // 创建用户注册线程
            Thread t = new Thread(new BatchRabbitTester(i));
            // 启动线程
            t.start();
        }
        // 等待线程执行完成
        // 为了阻塞测试用例的结束，因为我们测试用户完成方法后会自动停止，不会去等待其他线程执行完成，所以这里我们阻塞
        // 测试主线程来完成发送注册线程请求逻辑。
        Thread.sleep(2000);
    }

    /**
     * 批量注册用户测试线程类
     */
    class BatchRabbitTester implements Runnable {

        private int index;

        public BatchRabbitTester(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            try {
                mockMvc.perform(MockMvcRequestBuilders.post("/provider/saveUser")
                        .param("username", "testRabbitMQ" + index)
                        .param("password", "123qwe")
                        .param("nickname", "testRabbitMQ" + index))
                        .andDo(MockMvcResultHandlers.log())
                        .andReturn();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

}
