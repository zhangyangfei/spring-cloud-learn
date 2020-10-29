# 服务治理中心

eureka-server：端口700X

# 网关

app-server：app服务端，端口800X，调用微服务，测试调用微服务的工程

app-zuul：app网关，端口810X，调用微服务

app-gateway：app网关，端口820X

# 微服务

service-user：用户，端口900X

service-product：产品，端口1000X

# 熔断监控

turbine-server：熔断监控聚合，端口1100X