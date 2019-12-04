package com.zyf.systembase.constant;

/**
 * 服务名称和id
 */
public enum ServiceConstants {

	zuul网关("zuul网关", "app-zuul"), 
	系统基础("系统基础", "system-base"), 
	eureka服务端("eureka服务端", "eureka-server"),
	应用层服务("应用层服务", "app-server"), 
	用户微服务("用户微服务", "service-user"), 
	产品微服务("产品微服务", "service-product");

	private ServiceConstants(String name, String id) {
		this.name = name;
		this.id = id;
	}

	private String name;

	private String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
