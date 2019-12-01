package com.zyf.appserver.config;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCircuitBreaker // 开启断路器
public class CircuitBreakerConfig {

}
