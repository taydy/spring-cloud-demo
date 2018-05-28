package com.zuul.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.zuul.gateway.filter.AuthFilter;
import com.zuul.gateway.filter.ErrorFilter;

@EnableZuulProxy
@SpringBootApplication
public class ZuulApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApiGatewayApplication.class, args);
	}
	
	@Bean
	public AuthFilter authFilter() {
		return new AuthFilter();
	}
	
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	
//	/**
//	 * 将形如 helloservice-v1 的服务映射为 /v1/helloservice
//	 * @return
//	 */
//	@Bean
//	public PatternServiceRouteMapper serviceRouteMapper() {
//		return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)", "${version}/${name}");
//	}
}
