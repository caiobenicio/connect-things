package br.com.thing.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class Publisher {

	public void publishOnTopic(String topic, String msg) {
		
		MqttClient myClient = InitMqtt.getinstance().getMyClient();
		
		MqttTopic publishOnTopic = myClient.getTopic(topic);
		
		int pubQoS = 0;
		MqttMessage message = new MqttMessage(msg.getBytes());
    	message.setQos(pubQoS);
    	message.setRetained(false);
    	
    	MqttDeliveryToken token = null;
    	
    	try {
			token = publishOnTopic.publish(message);
			token.waitForCompletion();
			Thread.sleep(100);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
