package br.com.thing.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class InitMqtt {
	
	private static InitMqtt instance = new InitMqtt();
	public static InitMqtt getinstance() {
		return instance == null? new InitMqtt() : instance;
	}
	
	private MqttConnectOptions connOpt;
	
	private MqttClient myClient;
	
	private static final String CLIENTID = "clientWeb";	
	
//	private Map<String, MqttClient> mapConnection = new ConcurrentHashMap<String, MqttClient>();
	
    //private String appServerMqtt;
    
	public MqttClient connect(String ipMqtt) {
		
		connOpt = new MqttConnectOptions();
		connOpt.setCleanSession(true);
		connOpt.setKeepAliveInterval(30);
		
		try {
			myClient = new MqttClient(ipMqtt, CLIENTID);
			
			myClient.setCallback(new CallBack(CLIENTID));
			myClient.connect(connOpt);
			
//			String clientId = MqttClient.generateClientId();
//			map.put(clientId, myClient);
			
			Subscribe s = new Subscribe();
			s.subscribeTopic();
			
			//Publisher p = new Publisher();
			//p.publishOnTopic(myClient, topic, msg);
			
		} catch (MqttException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		System.out.println("Connected to " + ipMqtt);
		return myClient;
	}
	
	public MqttClient getMyClient() {
		return myClient;
	}
	
//	public MqttClient getMyClient(String key) {
//		return map.get(key);
//	}
}
