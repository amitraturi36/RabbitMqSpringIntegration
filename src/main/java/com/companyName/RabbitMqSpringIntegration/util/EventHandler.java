package com.companyName.RabbitMqSpringIntegration.util;

public class EventHandler {
	static void handler(Events event, Object message) {
		switch (event) {
		case Event1:
			System.out.println("publish 1 event" + message);
			break;
		case Event2:
			System.out.println("publish event 2" + message);
			break;
		}
	}
}
