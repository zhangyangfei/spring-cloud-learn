package com.zyf.appgetewaysentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppGatewaySentinelApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppGatewaySentinelApplication.class, args);
        System.out.println("app-gateway-sentinel启动完成！");
    }
}
