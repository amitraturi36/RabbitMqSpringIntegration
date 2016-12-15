package com.companyName.RabbitMqSpringIntegration.util;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public abstract class Connector {
	protected Channel channel;
	protected Connection connection;
	protected String queueName;

	public Connector(String queueName) throws IOException, TimeoutException {
		this.queueName = queueName;
		ConnectionFactory factory = new ConnectionFactory();
		// Hostname of your rabbitmq server
		factory.setHost("localhost");
		// getting a connection
		connection = factory.newConnection();

		// creating a channel
		channel = connection.createChannel();

		// declaring a queue for this channel. If queue does not exist,
		// it will be created on the server.
		channel.queueDeclare(queueName, false, false, false, null);
	}

	public void close() throws IOException, TimeoutException {
		this.channel.close();
		this.connection.close();
	}
}
