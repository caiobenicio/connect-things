package br.com.thing.websocket;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import br.com.thing.dto.MessageMqtt;
import br.com.thing.mqtt.MqttConnection;
import br.com.thing.mqtt.Publisher;

@Component
public class SocketTextHandler extends TextWebSocketHandler {

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		SessionUser.getInstance().getSessions().put(session.getId(), new ConcurrentWebSocketSessionDecorator(session, (int) TimeUnit.SECONDS.toMillis(10), 5 * 1024));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		SessionUser.getInstance().getSessions().remove(session.getId());
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {

		String msgType = null;
		String payload = message.getPayload();
		JSONObject jsonObject = new JSONObject(payload);

		Long user = Long.valueOf(jsonObject.get("user").toString());
		SessionUser.getInstance().getUserSession().put(user, session.getId());

		if (!jsonObject.isNull("msgType") && jsonObject.get("msgType").toString().length() == 1) {
			msgType = jsonObject.get("msgType").toString();

			new Publisher(MqttConnection.CLIENT_ID, jsonObject.get("topic").toString(),
					new MessageMqtt(user, null, msgType));
		}

	}

//	protected void broadcast(String data) {
//		final TextMessage message = new TextMessage(data);
//		sessions.forEach((key, session) -> {
//			try {
//				session.sendMessage(message);
//			} catch (IOException e) {
//			}
//		});
//	}

}