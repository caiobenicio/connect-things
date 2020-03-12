package br.com.thing.websocket;

import static java.lang.String.format;

import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.thing.mqtt.Subscribe;
import br.com.thing.websocket.SendMessages.MessageType;

@Controller
@EnableScheduling
public class WebSocketSenderService {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketSenderService.class);
	private JsonNode rootNode;
//	private final RabbitTemplate rabbitTemplate;
	private final Subscribe s = null;

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

//	public WebSocketSenderService(final RabbitTemplate rabbitTemplate) {
//		this.rabbitTemplate = rabbitTemplate;
//	}

	@MessageMapping("/chat/{roomId}/sendMessage")
	public void sendMessage(@DestinationVariable String roomId, @Payload JsonNode message) throws MqttException {
		messagingTemplate.convertAndSend(format("/channel/%s", roomId), message);

		logger.info("Sending message...");
		
		
		//new Subscribe("caio/safado");
//		rabbitTemplate.convertAndSend(ConfigExchangeQueue.EXCHANGE_NAME_TOPIC,
//				ConfigExchangeQueue.ROUTING_KEY, returnObj(message));
	}


	@MessageMapping("/chat/{roomId}/addUser")
	public void addUser(@DestinationVariable String roomId, @Payload SendMessages chatMessage,
			SimpMessageHeaderAccessor headerAccessor) {
		String currentRoomId = (String) headerAccessor.getSessionAttributes().put("room_id", roomId);
		if (currentRoomId != null) {
			SendMessages leaveMessage = new SendMessages();
			leaveMessage.setType(MessageType.LEAVE);
			leaveMessage.setSender(chatMessage.getSender());
			messagingTemplate.convertAndSend(format("/channel/%s", currentRoomId), leaveMessage);
		}
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		messagingTemplate.convertAndSend(format("/channel/%s", roomId), chatMessage);
	}

	public void receiveMessage(final Message message) {

		ObjectMapper objectMapper = new ObjectMapper();
		
		SendMessages leaveMessage = new SendMessages();
		leaveMessage.setType(MessageType.LEAVE);
		
		try {
			rootNode = objectMapper.readTree(message.getPayload().toString());
			String destination = rootNode.path("destination").textValue();
			leaveMessage.setSender(rootNode.path("body").textValue());	
			
			messagingTemplate.convertAndSend(format("/channel/%s", destination), leaveMessage );
			logger.info("Body: " + rootNode.path("body").textValue());
			
		} catch (IOException e) {
			logger.info("Error to map recieve message... " + e.getMessage());
		}
		
	}
		
}
