package br.com.thing.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;

import br.com.thing.utils.TopicConstant;

public class Subscribe {
	
	int subQoS = 0;
	
	public void subscribeTopic() {

		try {		
			
			MqttClient myClient = InitMqtt.getinstance().getMyClient();
			
			myClient.subscribe(TopicConstant.SENSOR_GARAGEM_PRESENCA, 1);
			myClient.subscribe(TopicConstant.SENSOR_SALA_TEMPERATURA, subQoS);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_SALA_TV, subQoS);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_QUARTO_LUZ, subQoS);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_QUARTO_TV, subQoS);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_BANHEIRO_LUZ, subQoS);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_VARANDA_LUZ, subQoS);
//			myClient.subscribe(TopicConstant.DISPOSITIVO_GARANGEM_LUZ, subQoS);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


//private static Subscribe instance = new Subscribe();
//public static Subscribe getInstance() {
//	return instance == null? new Subscribe() : instance;
//}