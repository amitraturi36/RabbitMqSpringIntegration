package com.companyName.RabbitMqSpringIntegration.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import com.rabbitmq.client.AMQP.BasicProperties;
import org.apache.commons.lang.SerializationUtils;

public class QueueConsumer extends Connector implements Runnable, Consumer {

	public QueueConsumer(String queueName) throws IOException, TimeoutException {
		super(queueName);
	}

	public void run() {
		try {
			// start consuming messages. Auto acknowledge messages.
			channel.basicConsume(queueName, true, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Called when consumer is registered.
	 */
	public void handleConsumeOk(String consumerTag) {
		System.out.println("Consumer " + consumerTag + " registered");

	}

	/**
	 * Called when new message is available.
	 */
	public void handleDelivery(String consumerTag, Envelope env, BasicProperties props, byte[] body)
			throws IOException {
		Map map = (HashMap) SerializationUtils.deserialize(body);
		System.out.println("Event= " + map.get("event")+" message=" + " received.");
		EventHandler.handler((Events) map.get("event"), map.get("message"));
		System.out.println("event triggered");

	}

	public void handleCancel(String consumerTag) {
		System.out.println("consumer canceled---------------------------------------");
	}

	public void handleCancelOk(String consumerTag) {
	}

	public void handleRecoverOk(String consumerTag) {
	}

	public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {
	}
}
