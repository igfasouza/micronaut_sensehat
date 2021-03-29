package com.example;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import rpi.sensehat.api.SenseHat;

@Controller("/start")
public class KafkaController {
	
	private static final Logger LOG = LoggerFactory.getLogger(KafkaController.class);
	
	@Inject
	Producer producer;
	
	@Get()
	public Map<String, Object> create() {
		

		LOG.info("Start ");
		
		SenseHat senseHat = new SenseHat();
		float humidity = senseHat.environmentalSensor.getHumidity();
		float pressure = senseHat.environmentalSensor.getPressure();
		float temperature = senseHat.environmentalSensor.getTemperature();

		producer.sendMessage("humidity" , Float.toString(humidity));
		producer.sendMessage("pressure" , Float.toString(pressure));
		producer.sendMessage("temperature" , Float.toString(temperature));
		
		
		final Map<String, Object> model = new HashMap<>();
		model.put("title", "");
		return model;
	}

}
