/**
 * 
 */
package com.cloud.stream.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author taydy
 * 2018/06/01
 */
@EnableBinding(SinkSender.class)
@Component
public class Producer {
	
	@Autowired
    private SinkSender sinkSender;
	
//	@Bean
//	@InboundChannelAdapter(value = Sink.INPUT, poller = @Poller(fixedDelay = "2000"))
//	public MessageSource<String> timerMessageSource() {
//		return () -> new GenericMessage<>("{\"from\":\"sink sender\"}");
//	}

    @Scheduled(fixedRate = 2000)
    public void produceHotDrinks() {
    		sinkSender.output().send(
                MessageBuilder.withPayload("{\"from\":\"sink sender\"}").build());
    }

}
