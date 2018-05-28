package com.ribbon.consumer.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

import rx.Observable;
import rx.Subscriber;

@Service
public class HelloService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${server.hello.url}")
	private String helloUrl;
	
	@HystrixCommand(fallbackMethod = "helloFallback",
			ignoreExceptions = {RuntimeException.class},
			commandKey = "helloService",
			groupKey = "hello_service_group",
			threadPoolKey = "hello_service_thread")
	@CacheResult(cacheKeyMethod = "getCacheKey")
	public String helloService(String name) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		return restTemplate.getForEntity(helloUrl + "?name={name}", String.class, params).getBody();
	}
	
	public String getCacheKey(String name) {
		return "helloService" + name;
	}
	
	public String helloFallback(String name, Throwable e) {
		return String.format("request %s error: %s", name, e.getMessage());
	}
	
	@HystrixCommand(fallbackMethod = "helloFallback",
			observableExecutionMode = ObservableExecutionMode.EAGER)
	public Observable<String> helloServiceObservable(String name) {
		return Observable.create(new Observable.OnSubscribe<String>() {

			@Override
			public void call(Subscriber<? super String> observer) {
				try {
					if (!observer.isUnsubscribed()) {
						Map<String, String> params = new HashMap<String, String>();
						params.put("name", name);
						String data = restTemplate.getForEntity(helloUrl, String.class, params).getBody();
						observer.onNext(data);
						observer.onCompleted();
					}
				} catch (Exception e) {
					observer.onError(e);
				}
				
			}
			
		});
	}
	
	@HystrixCommand(fallbackMethod = "helloFallback")
	public AsyncResult<String> helloServiceAsync(String name) {
		return new AsyncResult<String>() {
			@Override
			public String invoke() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("name", name);
				return restTemplate.getForEntity(helloUrl, String.class, params).getBody();
			}
		};
	}

}
