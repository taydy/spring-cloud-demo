package com.cloud.stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import com.cloud.stream.sender.SinkSender;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StreamHelloApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StreamHelloApplicationTests {
	
	@Autowired
	private SinkSender sinkSender;

	@Test
	public void contextLoads() {
		sinkSender.output().send(MessageBuilder.withPayload("from sink sender").build());
	}

}
