package com.zyf.appgateway.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * 消息生产者
 */
@Component
@EnableBinding(MyChannel.class) // 绑定渠道
public class MessageProducer {

    @Autowired
    MessageChannel myoutput;

    public void send(Object obj) {
        myoutput.send(MessageBuilder.withPayload(obj).build());
    }

}
