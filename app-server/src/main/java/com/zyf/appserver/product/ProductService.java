package com.zyf.appserver.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * feign声明式调用微服务（实现了负载均衡）
 */
@FeignClient("service-product") // 指定微服务id
public interface ProductService {

	// 指定微服务的请求方法
	@GetMapping("/productRestController/product/{id}")
	public Product getById(@PathVariable String id);

	@PostMapping("/productRestController/product")
	public Product insert(@RequestBody Product product);

}
