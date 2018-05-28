package com.feign.consumer.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feign.consumer.service.HelloService;

@RestController
public class ConsumerController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	HelloService helloService;
	
	@GetMapping(value = "/feign-consumer")
	public String helloConsumer(@RequestParam(value = "name", required = false) String name) {
		logger.info(String.format("/hello?name=%s", name));
		return helloService.helloService(name);
	}

}
