package com.zyf.appgateway.filter;

import com.zyf.appgateway.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义全局过滤器
 * <p>
 * 作用所有路由
 */
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 过滤器逻辑
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        String access_token = queryParams.getFirst("access_token");
        System.out.println(IpUtil.getIpAddr(request));
//        if (StringUtils.isEmpty(access_token)) {
//            logger.info("access_token为空，无法访问");
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
        System.out.println(exchange.getRequest().getPath().toString());
        return chain.filter(exchange);
    }

    /**
     * 过滤器顺序
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
