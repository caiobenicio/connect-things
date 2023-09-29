package br.com.thing.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.mqtt.MqttConnection;
import br.com.thing.mqtt.Publisher;

@RestController
@RequestMapping(path = ServicePaths.TASK_PATH)
public class TaskService {

    /**
     * @param topic
     * @param command
     * @return
     */
    @PostMapping()
    public ResponseEntity<?> publishTopic(@RequestParam(name = "command") String command) {
        new Publisher(MqttConnection.CLIENT_ID, "clientweb/inTopic", command);
        return ResponseEntity.status(HttpStatus.OK).body("Comando enviado!");
    }
}
