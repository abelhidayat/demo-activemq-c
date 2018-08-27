package com.abel.activemqc.demoactivemqc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.ConsumerQueueSecondService;
import service.ConsumerQueueService;
import service.ConsumerTopicSecondService;
import service.ConsumerTopicService;

import javax.jms.JMSException;

@SpringBootApplication
public class DemoActivemqCApplication {

	public static void main(String[] args) throws JMSException{
		SpringApplication.run(DemoActivemqCApplication.class, args);
		ConsumerQueueService consumerQueueService = new ConsumerQueueService();
//		ConsumerTopicService consumerTopicService = new ConsumerTopicService();
//		ConsumerQueueSecondService consumerQueueSecondService = new ConsumerQueueSecondService();
//		ConsumerTopicSecondService consumerTopicSecondService = new ConsumerTopicSecondService();
	}
}
