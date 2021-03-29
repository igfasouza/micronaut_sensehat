package com.example;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface Producer {

	@Topic("test")
	void sendMessage(@KafkaKey String key, String message);
	void sendMessage(@Topic String topic, @KafkaKey String key, String message);

}