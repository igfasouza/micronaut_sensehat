package com.example;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.annotation.Async;
import rpi.sensehat.api.SenseHat;

public class Application {

	Producer producer;

	public Application(Producer producer) {
		this.producer = producer;
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = Micronaut.run(Application.class);
	}

	@EventListener
	@Async
	public void onStartup(StartupEvent event) {

		SenseHat senseHat = new SenseHat();
		float humidity = senseHat.environmentalSensor.getHumidity();
		float pressure = senseHat.environmentalSensor.getPressure();
		float temperature = senseHat.environmentalSensor.getTemperature();

		producer.sendMessage("humidity" , Float.toString(humidity));
		producer.sendMessage("pressure" , Float.toString(pressure));
		producer.sendMessage("temperature" , Float.toString(temperature));

	}

}