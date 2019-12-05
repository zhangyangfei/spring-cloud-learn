package com.zyf.appserver.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.zyf.appserver.exception.NotFoundException;
import com.zyf.systembase.constant.ServiceConstants;


@Controller
@ResponseBody // 请求结果默认转换为json
public class UserController {
	
	private static final String SERVICE_USER="service-user";// 微服务配置:微服务名称spring.application.name

	// rest请求模板-非负载均衡调用，只能使用IP地址
	RestTemplate restTemplate = new RestTemplate();
	
	// rest请求模板-负载均衡调用
	@Autowired
	private RestTemplate servcieRestTemplate;
	
	@RequestMapping("/insert")
	public User insert(@RequestBody User user) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<User> reqeust = new HttpEntity<>(user, headers);
		// post请求
		return restTemplate.postForObject("http://localhost:9001/userRestController/user", reqeust, User.class);
	}
	
	/*
	 * 非负载均衡调用：只能使用IP地址（调用方不需要注册到服务治理中心）
	 * 负载均衡调用：调用双方都必须注册到服务治理中心（如果调用方未注册，报错：IllegalStateException No instances available for 被调用的serviceId）
	 */
	@RequestMapping(value = "/getUser/{id}")
	public User getUser(@PathVariable int id) {
		// get请求
		 User user = restTemplate.getForObject("http://localhost:9001/userRestController/user/{id}", User.class, id);
		// User user2 = servcieRestTemplate.getForObject("http://"+SERVICE_USER+"/userRestController" + "/user/{id}", User.class, id);
		User user2 = null;
		for(int i =1;i<=10;i++){
			user2 = 
			servcieRestTemplate.getForObject("http://"+ServiceConstants.用户微服务.getId()+"/userRestController" + "/user/{id}", User.class, i);
		}
		return user2;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getUsers/{id}/{name}")
	public List<User> getUsers(@PathVariable String id, @PathVariable String name) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("name", name);
		// get请求
		ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:9001/userRestController" + "/user/{id}/{name}",List.class, param);
		List<User> users = responseEntity.getBody();
		if (CollectionUtils.isEmpty(users)) {
			throw new NotFoundException("001", "没有相关用户");
		}
		return users;
	}

	 @RequestMapping("/update/{id}") // 更新全部属性
	public User update(@PathVariable int id, @RequestBody User user) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<User> reqeust = new HttpEntity<>(user, headers);
		// put请求
		restTemplate.put("http://localhost:9001/userRestController/user/{id}", reqeust,id);
		return user;
	}

	@RequestMapping("/updatepatch/{id}/{name}/{note}") // 更新部分属性
	public User updatepatch(@PathVariable int id, @PathVariable String name, @PathVariable String note) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("name", name);
		param.put("note", note);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Map<String, Object>> reqeust = new HttpEntity<>(param, headers);
		// patch请求
		// RestTemplate的底层是通过HttpURLConnection实现的（注意:java.net.HttpURLConnection.setRequestMethod 不支持PATCH方法，无法将请求发送出去）。
		return restTemplate.patchForObject("http://localhost:9001/userRestController/user/{id}/{name}/{note}", reqeust,
				User.class,param);
	}

	 @RequestMapping("/delete")
	 public User delete(int id) {
		 // delete请求
		 restTemplate.delete("http://localhost:9001/userRestController/user/{id}", id);
		 return new User(1, "吉姆格林", 1, "rest风格，删除");
	 }

}
