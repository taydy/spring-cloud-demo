package com.zuul.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 权限校验过滤器。
 * @author taydy
 *
 */
public class AuthFilter extends ZuulFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		
		LOGGER.info("send {} request to {}", request.getMethod(), request.getRequestURI().toString());
		String token = request.getHeader("Authorization");
		if (!verifyToken(token)) {
			LOGGER.warn("token is invalid");
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(401);
			return null;
		}
		
		LOGGER.info("token is valid");
		return null;
	}
	
	/**
	 * 鉴权方法。
	 * @param token
	 * @return
	 */
	public boolean verifyToken(String token) {
		return true;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
