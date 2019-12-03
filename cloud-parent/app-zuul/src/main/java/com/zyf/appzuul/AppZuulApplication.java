package com.zyf.appzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy // 启动zuul代理
public class AppZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppZuulApplication.class, args);
		System.out.println("spring-zuul服务启动完成！");

		// 以下链接访问product微服务
		// http://localhost/service-product/productRestController/product/1
		// http://localhost/product/productRestController/product/2
	}

}
