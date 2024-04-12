package br.com.thing.mqtt;

import java.util.Arrays;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscribe {

	public Subscribe() {}

	public Subscribe(MqttClient clientMQTT, String topic) {
		if (clientMQTT != null && topic != null) {
			try {
				if (clientMQTT.getTopic(topic) != null) {
					clientMQTT.unsubscribe(topic);
				}

				clientMQTT.subscribe(topic, 0);
				System.out.println("Topicos assinados: " + topic);
			} catch (MqttException e) {
				e.printStackTrace();
			}			
		}
	}

	public void unsubscribe(MqttClient clientMQTT, String... topicos) {
		if (clientMQTT == null || !clientMQTT.isConnected() || topicos.length == 0) {
			return;
		}
		try {
			clientMQTT.unsubscribe(topicos);
			System.out.println("Topicos desassinados: " + topicos[0]);
		} catch (MqttException ex) {
			System.out.println(String.format("Erro ao se desinscrever no tópico %s - %s", Arrays.asList(topicos), ex));
		}
	}

}
