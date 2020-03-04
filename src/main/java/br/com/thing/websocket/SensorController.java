package br.com.thing.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import br.com.thing.dto.SensorDTO;

public class SensorController extends TextWebSocketHandler {

	  @MessageMapping("/sensor")
	  @SendTo("/topic/sensor")
	  public SensorDTO greeting(String message) throws Exception {
	    return new SensorDTO("temperatura", "casa/sala/temp", "20.0");
	  }
}
