package com.icm.dateroapi;

import com.icm.dateroapi.Config.MQTT.MqttSubscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = "com.icm.dateroapi.Config.MQTT")
public class DateroApiApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DateroApiApplication.class, args);
		MqttSubscriber mqttSubscriber = context.getBean(MqttSubscriber.class);
		// Llama a subscribeToTopic en la instancia
		mqttSubscriber.subscribeToTopic("prueba");
	}
}
