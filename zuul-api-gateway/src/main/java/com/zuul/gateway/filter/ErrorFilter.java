package com.zuul.gateway.filter;

import javax.servlet.http.HttpServletResponse;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class ErrorFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		Throwable throwable = context.getThrowable();
		context.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		context.set("error.exception", throwable.getCause());
		return null;
	}

	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		return 10;
	}

}
