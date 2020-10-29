package com.zyf.appgateway.filter;

import com.zyf.appgateway.util.IpUtil;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * RequestRateLimiter限流规则配置
 * <p>
 * 只能注入一个KeyResolver的bean
 */
@Configuration
public class KeyResolverConfig {

    /**
     * 请求路径限流
     */
    @Bean
    public KeyResolver pathKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().toString());
    }

    /**
     * 请求ip限流
     */
//    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(IpUtil.getIpAddr(exchange.getRequest()));
    }

    /**
     * 请求参数限流
     */
//    @Bean
    public KeyResolver userIdKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("id"));
    }
}
