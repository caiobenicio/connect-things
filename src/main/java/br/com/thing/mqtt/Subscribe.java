package br.com.thing.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import br.com.thing.utils.TopicConstant;

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

	public void subscribeTopic(String clientId) {

		try {

			myClient = MqttConnection.getInstance().getMyClient(clientId);
			String topicBase = TopicConstant.APPWEB_MQTT.replace("?", clientId);
			
			// topic base para receber o topico que a placa ira disponibilizar
			myClient.subscribe(topicBase, 1);

			//myClient.subscribe(TopicConstant.SENSOR_SALA_TEMPERATURA, subQoS);
		//	myClient.subscribe(TopicConstant.SENSOR_GARAGEM_PRESENCA, 1);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_SALA_TV, subQoS);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_QUARTO_LUZ, subQoS);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_QUARTO_TV, subQoS);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_BANHEIRO_LUZ, subQoS);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_VARANDA_LUZ, subQoS);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_GARANGEM_LUZ, subQoS);

			System.out.println("Topicos subscribe: " + topicBase);
		//	System.out.println("Topicos subscribe:" + TopicConstant.SENSOR_SALA_TEMPERATURA);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

//private static Subscribe instance = new Subscribe();
//public static Subscribe getInstance() {
//	return instance == null? new Subscribe() : instance;
//}