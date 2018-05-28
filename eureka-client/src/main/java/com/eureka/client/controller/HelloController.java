package com.eureka.client.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private DiscoveryClient client;
	
	@GetMapping(value = "/hello")
	public String helloWorld(@RequestParam(value = "name", required = false)String name) {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info(String.format("/hello?name=%s, host: %s, service id: %s", name, instance.getHost(), instance.getServiceId()));
		return String.format("Hello %s", name == null ? "world" : name);
	}

}
