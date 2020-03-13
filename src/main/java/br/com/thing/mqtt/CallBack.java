package br.com.thing.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.messaging.Message;

import br.com.thing.websocket.WebSocketSenderService;

public class CallBack implements MqttCallback {
	
	private String instanceData = "";
	private WebSocketSenderService ws = new WebSocketSenderService();

	public CallBack(String instance) {
		instanceData = instance;
	}

	@Override
	public void connectionLost(Throwable cause) {
		System.out.println("Connection lost on instance \"" + instanceData + "\" with cause \"" + cause.getMessage()
				+ "\" Reason code " + ((MqttException) cause).getReasonCode() + "\" Cause \""
				+ ((MqttException) cause).getCause() + "\"");
		cause.printStackTrace();
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		try {
			
			ws.receiveMessage(message);
			System.out.println("Mensagem recebida: " + message.toString() + "|| No topico \"" + topic.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		try {
			System.out.println("Recebido da instancia: " + instanceData + "");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}