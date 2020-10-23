package com.zyf.appserver.user;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody // 请求结果默认转换为json
@RequestMapping("/user")
@DefaultProperties(defaultFallback = "getUserFallback") // 默认的降级方法，多个方法的降级处理
public class UserController2 {

    /**
     * feign声明接口
     */
    @Autowired
    private UserService userService;

    @RequestMapping("getUser/{id}")
    @HystrixCommand
    public User getUser(@PathVariable int id) {
        return userService.getById(id);
    }

    /**
     * 默认的降级方法，不能带形参，否则报错（fallback method wasn't found）
     */
    public User getUserFallback() {
        return new User(0, "请求超时", 1, "");
    }

}
