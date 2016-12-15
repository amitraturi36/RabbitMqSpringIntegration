package com.companyName.RabbitMqSpringIntegration.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;

import com.companyName.RabbitMqSpringIntegration.util.Constants;
import com.companyName.RabbitMqSpringIntegration.util.Events;
import com.companyName.RabbitMqSpringIntegration.util.Producer;
import com.companyName.RabbitMqSpringIntegration.util.QueueConsumer;

@Service
public class EventPublisherService {
	final private Producer producer;

	EventPublisherService() throws IOException, TimeoutException {
		QueueConsumer consumer = new QueueConsumer(Constants.queueName);
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
		producer = new Producer(Constants.queueName);
	}

	public void publishEvent(Events event, Object messages) throws IOException, TimeoutException {

		HashMap<String, Object> message = new HashMap<String, Object>();
		message.put("event", event);
		message.put("message", messages);
		producer.sendMessage(message);
	}
}
