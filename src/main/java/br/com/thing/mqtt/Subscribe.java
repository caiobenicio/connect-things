package br.com.thing.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import br.com.thing.utils.TopicConstant;

public class Subscribe {

	private MqttClient myClient = null;
	private int subQoS = 0;
	private String topic;
	
	public Subscribe() {
		super();
	}
	
	public Subscribe(String topic) throws MqttException {
		super();
		this.topic = topic;
		
		myClient = InitMqtt.getinstance().getMyClient();
		myClient.subscribe(topic, 1);
		System.out.println(topic);
	}


	public void subscribeTopic(String topic) {
		
		try {		
			
			myClient = InitMqtt.getinstance().getMyClient();
			myClient.subscribe(topic, 1);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void subscribeTopic() {

		try {

			myClient = InitMqtt.getinstance().getMyClient();

			// topic base para receber o topico que a placa ira disponibilizar
			myClient.subscribe(TopicConstant.APPWEB_MQTT);
			
			
			
			//myClient.subscribe(TopicConstant.SENSOR_SALA_TEMPERATURA, subQoS);
		//	myClient.subscribe(TopicConstant.SENSOR_GARAGEM_PRESENCA, 1);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_SALA_TV, subQoS);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_QUARTO_LUZ, subQoS);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_QUARTO_TV, subQoS);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_BANHEIRO_LUZ, subQoS);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_VARANDA_LUZ, subQoS);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_GARANGEM_LUZ, subQoS);

			System.out.println("Topicos subscribe: " + TopicConstant.APPWEB_MQTT);
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