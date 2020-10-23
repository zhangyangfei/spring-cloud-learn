package com.zyf.appserver.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.zyf.appserver") // 启动feign微服务负载均衡，需要指定包
public class FeignConfig {

}
