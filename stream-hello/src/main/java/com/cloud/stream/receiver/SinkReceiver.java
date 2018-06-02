/**
 * 
 */
package com.cloud.stream.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.alibaba.fastjson.JSONObject;


/**
 * @author taydy
 * 2018/06/01
 */
@EnableBinding(value = {Sink.class})
public class SinkReceiver {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SinkReceiver.class);
	
	@StreamListener(Sink.INPUT)
//	@SendTo(Processor.OUTPUT)  将处理结果发送到另一个消息通道
	public String receiver(JSONObject payload) {
		String msg = String.format("Received: %s", payload);
		LOGGER.info(msg);
		return msg;
	}
	
}
