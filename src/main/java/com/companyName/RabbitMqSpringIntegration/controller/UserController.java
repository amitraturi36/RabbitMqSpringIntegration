package com.companyName.RabbitMqSpringIntegration.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.companyName.RabbitMqSpringIntegration.service.EventPublisherService;
import com.companyName.RabbitMqSpringIntegration.util.Events;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	EventPublisherService eventPublisherService;

	@RequestMapping("/rabbitMq")
	public ModelAndView welcome() throws IOException, TimeoutException {
		Integer message1 = 11111;
		eventPublisherService.publishEvent(Events.Event1, message1);
		String message2 = "Implementing RabbitMQ";
		eventPublisherService.publishEvent(Events.Event2, message2);
		return new ModelAndView("index", "message", "Implemented");
	}

}
