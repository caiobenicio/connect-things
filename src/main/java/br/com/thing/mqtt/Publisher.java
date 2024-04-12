package br.com.thing.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;

import br.com.thing.dto.MessageMqtt;

public class Publisher {

	public Publisher() {

	}

	public Publisher(String clientId, Long id, Long boardId, String topic, String msgType) {
		MessageMqtt message = new MessageMqtt();
		message.setUser(id);
		message.setBoard(boardId);
		message.setMsgType(msgType);

		JSONObject jsonObject = new JSONObject(message);
		jsonObject.toString();

		MqttClient myClient = MqttConnection.getInstance().getMapConnection().get(clientId);
		MqttTopic publishOnTopic = myClient.getTopic(topic);

		int pubQoS = 0;
		MqttMessage msg = new MqttMessage(jsonObject.toString().getBytes());
		msg.setQos(pubQoS);
		msg.setRetained(false);	
		
		try {
			System.out.println("Publicado Topico: " + topic + " Mensagem: " + msg);
			MqttDeliveryToken token = publishOnTopic.publish(msg);
			token.waitForCompletion();
		} catch (Exception e) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
