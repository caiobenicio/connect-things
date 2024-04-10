package br.com.thing.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.thing.dto.MessageMqtt;

public class Publisher {

	public Publisher() {
	}

	public Publisher(MqttClient clientMQTT, String topic, String msg) {
		try {
			System.out.println("Publicado Topico: " + topic + " Mensagem: " + msg);
			clientMQTT.publish(topic, msg.getBytes(), 0, false);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public Publisher(String clientId, String topic, String msg) {
		MqttClient myClient = MqttConnection.getInstance().getMapConnection().get(clientId);
		MqttTopic publishOnTopic = myClient.getTopic(topic);

		int pubQoS = 0;
		MqttMessage message = new MqttMessage(msg.getBytes());
		message.setQos(pubQoS);
		message.setRetained(false);

		MqttDeliveryToken token = null;

		try {
			System.out.println("Publicado Topico: " + topic + " Mensagem: " + msg);
			token = publishOnTopic.publish(message);
			token.waitForCompletion();
			Thread.sleep(100);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Publisher(String clientid, String topic, MessageMqtt messageMqtt) {
		MqttClient myClient = MqttConnection.getInstance().getMapConnection().get(clientid);
		MqttTopic publishOnTopic = myClient.getTopic(topic);

		int pubQoS = 0;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		MqttMessage message = null;
		
		try {
			String json = ow.writeValueAsString(messageMqtt);
			
			message = new MqttMessage(json.getBytes());
			message.setQos(pubQoS);
			message.setRetained(false);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		MqttDeliveryToken token = null;

		try {
			System.out.println("Publicado Topico: " + topic + " Mensagem: " + message);
			token = publishOnTopic.publish(message);
			token.waitForCompletion();
			Thread.sleep(100);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
