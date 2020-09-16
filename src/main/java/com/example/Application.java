package com.example;

import javax.inject.Inject;

import io.micronaut.runtime.Micronaut;
import rpi.sensehat.api.SenseHat;

public class Application {

	@Inject
	static Producer producer;
	
    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
        
        SenseHat senseHat = new SenseHat();
        float humidity = senseHat.environmentalSensor.getHumidity();
        float pressure = senseHat.environmentalSensor.getPressure();
        float temperature = senseHat.environmentalSensor.getTemperature();
        
        producer.sendMessage("humidity" , Float.toString(humidity));
        producer.sendMessage("pressure" , Float.toString(pressure));
        producer.sendMessage("temperature" , Float.toString(temperature));
        
    }
    
}