package com.zyf.appserver.config;

import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Configuration;

/**
 * Hystrix监控台启动配置
 */
@EnableHystrixDashboard // 启动Hystrix监控台
@Configuration
public class HystrixDashboardConfig {
}
