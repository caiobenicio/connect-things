package br.com.thing.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.thing.dto.MessageMqttResponse;
import br.com.thing.websocket.SessionUser;

@Service
public class CallBack implements MqttCallback {

	private String instanceData = "";
	private WebSocketSession session;

	public CallBack(String instance) {
		instanceData = instance;
	}

	public CallBack() {
		super();
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
						session.sendMessage(new TextMessage(message.toString()));
					}

					// Optional<Client> client = userRepository.findById(msg.getUser());
					// List<Port> portList = new ArrayList<>();
					// if(client.isPresent() && client.get().getBoards().size() == 1) {
					// Client user = client.get();
					// for (String item : msg.getPinsIn()) {
					// Port p = new Port();
					// p.setPort(item);
					// p.setType(PortType.I);
					// portList.add(p);
					// }
					//
					// for (String out : msg.getPinsOut()) {
					// Port p = new Port();
					// p.setPort(out);
					// p.setType(PortType.O);
					// portList.add(p);
					// }
					//
					// user.getBoards().get(0).setPorts(portList);
					// Client update = userRepository.save(user);
					// System.out.println(update);
					// }
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		try {
			// System.out.println("Recebido da instancia: " + instanceData + ""+"\n mensagem:"+ token.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}