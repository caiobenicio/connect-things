package br.com.thing.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscribe {

	private MqttClient myClient;
	private int subQoS = 0;
	private String topic;
	
	public Subscribe() {
	}
	
	public Subscribe(String topic) throws MqttException {
		this.topic = topic;
		
		myClient = null;
		myClient.subscribe(topic, 1);
		System.out.println(topic);
	}

	public void subscribeTopic(String clientId, String clientMqtt, String subscribe) {
		try {
			myClient = MqttConnection.getInstance().getMyClient(clientId);
			
			// topic base para receber os pinos que a placa ira disponibilizar
			myClient.subscribe(subscribe, 1);

			System.out.println("Topicos subscribe: " + subscribe);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

//private static Subscribe instance = new Subscribe();
//public static Subscribe getInstance() {
//	return instance == null? new Subscribe() : instance;
//}