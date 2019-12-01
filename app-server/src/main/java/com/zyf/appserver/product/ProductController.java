package com.zyf.appserver.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Controller
@RequestMapping("/product")
@ResponseBody
public class ProductController {

	@Autowired
	private ProductService productService;

	// 调用微服务
	// http://localhost:8001/product/getById?id=1
	@RequestMapping("/getById/{id}")
	@HystrixCommand(fallbackMethod = "error", // 断路器-降级处理(默认超时时间1000ms，超时则调用fallbackMethod方法)
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100000") })
	public Product getById(@PathVariable String id) {
		// 调用微服务
		Product product = productService.getById(id);
		return product;
	}

	@RequestMapping("/insert")
	@HystrixCommand(fallbackMethod = "error")
	public Product insert(@RequestBody Product product) {
		// 调用微服务,测试负载均衡
		for (int i = 1; i <= 10; i++) {
			product.setId(i + "");
			productService.insert(product);
		}
		return product;
	}

	// 断路器-降级处理方法
	public Product error(String id) {
		System.out.println("连接超时！");
		return new Product("", "连接超时！");
	}
}
