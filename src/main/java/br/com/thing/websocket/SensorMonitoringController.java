package br.com.thing.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import br.com.thing.entity.Sensor;

@Controller
public class SensorMonitoringController {

    @MessageMapping("/hello")
    @SendTo("/topic/monitoring")
    public Sensor monitoring() throws Exception {
    	 return new Sensor();   
    }
}
