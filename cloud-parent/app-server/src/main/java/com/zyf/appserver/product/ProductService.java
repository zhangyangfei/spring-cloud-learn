package com.zyf.appserver.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * feign声明式调用微服务（实现了负载均衡）
 */
@FeignClient(value = "service-product", // 指定微服务id
		fallback = ProductServiceFallback.class) // 指定降级方法的实现类
public interface ProductService {

	// 指定微服务的请求方法。使用feign时，参数名要在@PathVariable中用value指明。
	@GetMapping("/productRestController/product/{id}")
	public Product getById(@PathVariable(value="id") String id);

	@PostMapping("/productRestController/product")
	public Product insert(@RequestBody Product product);

}
