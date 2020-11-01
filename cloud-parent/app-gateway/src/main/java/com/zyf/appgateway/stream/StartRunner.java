package com.zyf.appgateway.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 在系统启动成功后执行run()方法
 */
@Order(Integer.MIN_VALUE) // 指定执行熟悉
@Component
public class StartRunner implements CommandLineRunner {

    @Autowired
    MessageProducer messageProducer;

    @Override
    public void run(String... args) {
        for (int i = 0; i < 5; i++) {
            String msg = "告诉大伙，app-gateway启动完成！-->" + i;
            messageProducer.send(msg);
            System.out.println("通过消息中间件发送消息：" + msg);
        }
    }
}
