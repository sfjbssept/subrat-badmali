package com.active.consumer;

import org.springframework.jms.annotation.JmsListener;

public class Consumer {
	
	@JmsListener(destination = "standalone.queue")
	public void consume(String msg) {
		System.out.println("The message that has been published is consumed"+msg);
	}

}
