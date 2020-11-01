package com.zyf.serviceproduct.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 */
@Component
@EnableBinding(MyChannel.class)
public class MessageConsumer {

    @StreamListener(MyChannel.INPUT)
    public void receive(String message) {
        System.out.println("获取到中间件的消息: " + message);
    }
}
