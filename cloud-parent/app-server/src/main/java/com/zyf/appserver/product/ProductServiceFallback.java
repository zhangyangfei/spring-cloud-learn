package com.zyf.appserver.product;

import org.springframework.stereotype.Component;

/**
 * Feign对Hystrix降级方法的实现
 */
@Component
public class ProductServiceFallback implements ProductService {

    @Override
    public Product getById(String id) {
        System.out.println("主键查询连接超时！");
        return new Product("", "主键查询连接超时！");
    }

    @Override
    public Product insert(Product product) {
        System.out.println("存入产品连接超时！");
        return new Product("", "存入产品连接超时！");
    }
}
