package br.com.thing.mqtt;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import br.com.thing.utils.AppProperty;

public class MqttConnection {
	
	private static MqttConnection instance = new MqttConnection();
	private Map<String, MqttClient> mapConnection = new ConcurrentHashMap<String, MqttClient>();
	private MqttConnectOptions connOpt;
	private MqttClient myClient;
	private static final String CLIENTID = "clientweb";	
	
	public static MqttConnection getInstance() {
		return instance;
	}

	public MqttClient connect() {
		
		connOpt = new MqttConnectOptions();
		connOpt.setCleanSession(true);
		connOpt.setKeepAliveInterval(30);
		
		try {
			myClient = new MqttClient(AppProperty.getinstance().getMqttUrl(), CLIENTID);
			
			myClient.setCallback(new CallBack());
			myClient.connect(connOpt);
			
			mapConnection.put(CLIENTID, myClient);
						
			String sub = AppProperty.getinstance().getClientMqtt() + AppProperty.getinstance().getMqttSubscribe(); 
			Subscribe s = new Subscribe();
			s.subscribeTopic(CLIENTID, AppProperty.getinstance().getClientMqtt(), sub);
			
			String pub = AppProperty.getinstance().getClientMqtt() + AppProperty.getinstance().getMqttPublish();
			Publisher p = new Publisher();
			p.publishOnTopic(CLIENTID, pub, "A");
			
		} catch (MqttException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		//System.out.println("Connected to " + ipMqtt + "clientId=" + CLIENTID);
		return myClient;
	}
	
	public MqttClient connect(String clientId, String ipMqtt) {
		
		connOpt = new MqttConnectOptions();
		connOpt.setCleanSession(true);
		connOpt.setKeepAliveInterval(30);
		
		System.out.println("Connected to " + ipMqtt + " clientId=" + clientId);
		try {
			clientId = (clientId!=null? clientId : MqttClient.generateClientId().substring(0, 5));
			myClient = new MqttClient(ipMqtt, clientId);
			
			myClient.setCallback(new CallBack(clientId));
			myClient.connect(connOpt);
			
			mapConnection.put(clientId, myClient);
			
			Subscribe s = new Subscribe();
			//s.subscribeTopic(clientId);
			
			Publisher p = new Publisher();
			p.publishOnTopic(clientId, "appweb/"+clientId+"/home/sala", "ola mqtt");
			
		} catch (MqttException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return myClient;
	}
	
	public MqttClient getMyClient(String key) {
		return mapConnection.get(key);
	}
	
	public MqttClient removeClient(String key) {
		return mapConnection.remove(key);
	}
	
}
