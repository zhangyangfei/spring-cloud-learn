package com.zyf.serviceproduct.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productRestController")
public class ProductRestController {

	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable String id) throws InterruptedException {
		Long ms = (long) (2000L*Math.random());// 随机数，2000之内
		System.out.println(ms);
		Thread.sleep(ms);
		return new Product(id, "产品" + id);
	}

	@PostMapping("/product")
	public Product insert(@RequestBody Product product) throws InterruptedException {
		Long ms = (long) (2000L*Math.random());
		System.out.println(ms);
		Thread.sleep(ms);
		System.out.println("产品id=" + product.getId());
		return product;
	}
}
