package com.psa.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.psa.constants.AppConstants;
import com.psa.entity.Order;

@Configuration
public class KafkaConsumerConfig {
	@Bean
	public ConsumerFactory<String, Order> consumerFactory() {

		Map<String, Object> kafkaConfigProps = new HashMap<>();

		kafkaConfigProps .put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.KAFKA_HOST);
		kafkaConfigProps .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		kafkaConfigProps .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

		return new DefaultKafkaConsumerFactory<>(kafkaConfigProps, new StringDeserializer(), new JsonDeserializer<>());

	}
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Order> kafkaListnerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, Order> factory = 
				new ConcurrentKafkaListenerContainerFactory<>();

		factory.setConsumerFactory(consumerFactory());

		return factory;
	}

}
