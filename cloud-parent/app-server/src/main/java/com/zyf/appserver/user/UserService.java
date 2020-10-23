package com.zyf.appserver.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-user")
public interface UserService {

	// 指定微服务的请求方法。使用feign时，参数名要在@PathVariable中用value指明。
	@GetMapping("/userRestController/user/{id}")
	public User getById(@PathVariable(value = "id") int id);

}
