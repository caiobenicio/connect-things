package br.com.thing.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.thing.dto.MessageMqttResponse;
import br.com.thing.context.ApplicationContextProvider;
import br.com.thing.websocket.SessionUser;

@Service
public class CallBack implements MqttCallback {

	private String instanceData = "";
	private WebSocketSession session;
	
	public CallBack() {	super(); }
	public CallBack(String instance) {
		instanceData = instance;
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		if (message.toString().length() > 1) {
			try {

				System.out.println("Mensagem recebida: " + message.toString() + " || No topico: " + topic.toString());
				MessageMqttResponse msg = new ObjectMapper().readValue(message.toString(), MessageMqttResponse.class);

				if (msg.getMsgType().equals("S")) {
					if (session != null) {
						session.sendMessage(new TextMessage(message.toString()));
					}

				} else if (msg.getMsgType().equals("P")) {
					if (msg.getUser() != null) {
						String sessionId = SessionUser.getInstance().getUserSession().get(msg.getUser());

						if (sessionId != null) {
							session = SessionUser.getInstance().getSessions().get(sessionId);
							
							if (session.isOpen()) {
								session.sendMessage(new TextMessage(message.toString()));
							}
						}

						savePort(msg);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		try {
			// System.out.println("Recebido da instancia: " + instanceData + ""+"\n
			// mensagem:"+ token.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void connectionLost(Throwable cause) {
		System.out.println("Connection lost on instance \"" + instanceData + "\" with cause \"" + cause.getMessage()
				+ "\" Reason code " + ((MqttException) cause).getReasonCode() + "\" Cause \"");
		cause.printStackTrace();
	}

	private void savePort(MessageMqttResponse msg) {
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();						
		AutowireCapableBeanFactory factory = context.getAutowireCapableBeanFactory();

		SavePort savePort = new SavePort(msg);
		factory.autowireBean( savePort );
		factory.initializeBean( savePort, "savePort" );
		savePort.save();
	}
}