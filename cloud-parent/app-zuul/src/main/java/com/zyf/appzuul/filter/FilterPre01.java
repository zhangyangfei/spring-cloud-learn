package com.zyf.appzuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * zuul过滤器pre01
 */
@Component
public class FilterPre01 extends ZuulFilter {

	// 是否应该执行该过滤器，如果是false，则不执行该filter
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String id = request.getParameter("id");
		// 如果id是空，则不开启过滤，直接放行
		return !StringUtils.isEmpty(id);
	}

	// 执行业务操作，可执行sql,nosql等业务
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String id = request.getParameter("id");

		// 如果id=1，则继续执行下一个filter或转发请求
		if ("1".equals(id)) {
			// 会进行路由，继续执行下一个filter或转发请求
			ctx.setSendZuulResponse(true);
			ctx.setResponseStatusCode(200);
			// 可以把一些值放到ctx中，便于后面的filter获取使用
			ctx.set("isOK", true);
		} else {
			// 不需要进行路由，不会转发请求
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.set("isOK", false);
			// 返回错误内容给客户端
			ctx.setResponseBody((ctx.getResponseBody() == null ? "" : ctx.getResponseBody())
					+ "{\"error\":\"id mast be 1\"}");
		}
		return null;
	}

	// 过滤器类型
	// 顺序: pre ->routing -> post ,以上3个顺序出现异常时都可以触发error类型的filter
	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	// 同filterType类型中，order值越大，优先级越低
	@Override
	public int filterOrder() {
		return 0;
	}

	// 测试访问地址： http://localhost/product/productRestController/product/5?id=2&name=zhangsan1
	
	
}
