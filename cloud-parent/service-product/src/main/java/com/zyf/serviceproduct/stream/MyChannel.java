package com.zyf.serviceproduct.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 消息渠道配置
 */
public interface MyChannel {

    // 输出渠道ID：yml配置cloud.stream:.bindings.本OUTPUT字符串.destination=exchange(交换机)名称
    String OUTPUT = "myoutput";

    // 输入渠道ID：yml配置cloud.stream:.bindings.本OUTPUT字符串.destination=exchange(交换机)名称
    String INPUT = "myinput";

    // 输出渠道
    @Output(OUTPUT)
    MessageChannel myoutput();

    // 输入渠道
    @Input(INPUT)
    SubscribableChannel myinput();

}
