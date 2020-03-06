package br.com.thing.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import br.com.thing.dto.SensorDTO;

@Controller
@RequestMapping("/")
public class SensorController extends TextWebSocketHandler {

	@Autowired
	private SimpMessagingTemplate webSocket;

	@MessageMapping("/sensor")
	@SendTo("/clientWeb/sensor")
	public SensorDTO greeting(String message) throws Exception {
	//	CallBack r = new CallBack( this );
      //  new Thread(r).start();

        return new SensorDTO(null, "Hello world !", message, message);
    }

    public void fireGreeting() {
        System.out.println("Fire");
        this.webSocket.convertAndSend("/clientWeb/sensor", new SensorDTO(null, "Fire", null, null));
    }
}
