package com.ribbon.consumer.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.ribbon.consumer.service.HelloService;

@RestController
public class ConsumerController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private HelloService helloService;
	
	@GetMapping(value = "/ribbon-consumer")
	public String helloConsumer(@RequestParam(value = "name", required = false) String name) {
		logger.info(String.format("/hello?name=%s", name));
		// 使用 @CacheResult 需要初始化上下文
		HystrixRequestContext.initializeContext();
		return helloService.helloService(name);
	}

}
