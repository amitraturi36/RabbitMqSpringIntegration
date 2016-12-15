package com.companyName.RabbitMqSpringIntegration.util;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.SerializationUtils;

public class Producer extends Connector {
	public Producer(String queueName) throws IOException, TimeoutException{
		
		super(queueName);
	}
	public void sendMessage(Serializable object) throws IOException {
	    channel.basicPublish("",queueName, null, SerializationUtils.serialize(object));
	}	
}