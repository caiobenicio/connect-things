package br.com.thing.mqtt;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import br.com.thing.utils.AppProperty;

public class MqttConnection {

	private static MqttConnection instance;
	private Map<String, MqttClient> mapConnection = new ConcurrentHashMap<String, MqttClient>();
	public static String CLIENT_ID;
	UUID uuid = UUID.randomUUID();

	public MqttConnection() {
		CLIENT_ID = "homeon-" + uuid;
	}

	public static synchronized MqttConnection getInstance() {
		if (instance == null)
			instance = new MqttConnection();

		return instance;
	}

	public void connect(String mqttServer, String topicSub, String topicPub) {
		try {
			System.out.println("Connected to " + AppProperty.getinstance().getMqttUrl() + " MQTT ID = " + CLIENT_ID);

			MqttClient client = new MqttClient(AppProperty.getinstance().getMqttUrl(), CLIENT_ID, new MemoryPersistence());
			MqttConnectOptions options = new MqttConnectOptions();
			//options.setUserName(username);
			//options.setPassword(password.toCharArray());
			client.connect(options);
			client.setCallback(new CallBack(CLIENT_ID));
			getMapConnection().put(CLIENT_ID, client);

//			if (topicSub != null) 
//				new Subscribe(getMapConnection().get(CLIENTID), topicSub);
//			
//			if (topicPub != null) {
//				new Publisher(getMapConnection().get(CLIENTID), topicPub, "P");
//				new Publisher(getMapConnection().get(CLIENTID), topicPub, "0:1");
//				
//			}

		} catch (MqttException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public synchronized MqttClient removeClient(String key) {
		return mapConnection.remove(key);
	}

	public synchronized Map<String, MqttClient> getMapConnection() {
		return mapConnection;
	}

	public void disconnect(String topicBasic) {
//		if (myClient == null || !myClient.isConnected()) {
//			return;
//		}
//		try {
//			myClient.disconnect();
//			myClient.close();
//			removeClient(topicBasic);
//		} catch (MqttException ex) {
//			System.out.println("Erro ao desconectar do broker mqtt - " + ex);
//		}
	}
}
