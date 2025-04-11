package com.psa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.psa.constants.AppConstants;
import com.psa.entity.Order;

@Service
public class OrderService {


		@Autowired
		private KafkaTemplate<String, Order> kafkaTemplate;
        //URL of Kafka,JsonSerializer, StringSerializer(No).
		public String addMsg(Order order) {

			// sends msg to kafka topic
			kafkaTemplate.send(AppConstants.TOPIC,order.getId(), order);

			return "Msg Published To Kafka Topic";
		}
	}