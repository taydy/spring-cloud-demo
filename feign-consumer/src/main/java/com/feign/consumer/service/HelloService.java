package com.feign.consumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "hello-service", fallback = HelloServiceFallback.class)
public interface HelloService {
	
	@GetMapping("/hello")
	String helloService(@RequestParam("name") String name);

}
