package com.icm.dateroapi.config.MQTT;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttSubscriber {

    @Autowired
    private IMqttClient mqttClient;

    public void subscribeToTopic(String topic) {
        try {
            mqttClient.subscribe(topic, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String payload = new String(message.getPayload());
                    System.out.println("Mensaje MQTT recibido en el tema " + topic + ": " + payload);
                    // Procesa el mensaje según tus necesidades
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}