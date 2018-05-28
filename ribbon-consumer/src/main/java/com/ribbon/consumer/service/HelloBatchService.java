package com.ribbon.consumer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class HelloBatchService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${server.hello.url}")
	private String helloUrl;
	
	@HystrixCollapser(batchMethod = "findAll",
			collapserProperties = {
					@HystrixProperty(name = "timerDelayInMilliseconds", value = "100")
			})
	public String hello(String name) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		return restTemplate.getForEntity(helloUrl, String.class, params).getBody();
	}
	
	@HystrixCommand
	public List<String> helloAll(List<String> names) {
		List<String> results = Lists.newArrayList();
		names.forEach(name -> {
			Map<String, String> params = new HashMap<String, String>();
			params.put("name", name);
			results.add(restTemplate.getForEntity(helloUrl, String.class, params).getBody());
		});
		return results;
	}

}
