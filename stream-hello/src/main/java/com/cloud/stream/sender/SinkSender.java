/**
 * 
 */
package com.cloud.stream.sender;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;

/**
 * @author taydy
 * 2018/06/01
 */
public interface SinkSender {
	
	@Output(Source.OUTPUT)
	MessageChannel output();

}
