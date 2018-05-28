package com.feign.consumer.service;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceFallback implements HelloService {

	@Override
	public String helloService(String name) {
		return String.format("request %s error", name);
	}

}
