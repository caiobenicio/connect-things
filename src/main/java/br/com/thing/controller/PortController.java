package br.com.thing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Port;
import br.com.thing.mqtt.MqttConnection;
import br.com.thing.mqtt.Publisher;
import br.com.thing.repository.PortRepository;
import br.com.thing.service.GenericService;
import br.com.thing.service.ServicePaths;

@RestController
@RequestMapping(path = ServicePaths.PORT_PATH)
public class PortController extends GenericService<Port, Long> {
	
	@Autowired
	private PortRepository portRepository;  
	
    @GetMapping(value = "/findByPorts")
    public ResponseEntity<?> findByPorts(@RequestParam(name = "id") Long id
    , @RequestParam(name = "topic") String topic , @RequestParam(name = "boardId") Long boardId) {
        if (topic == "null") {
            topic = "clientweb/inTopic";
        }
        new Publisher(MqttConnection.CLIENT_ID, id, boardId, topic, "P");
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
    }
    
	@GetMapping(value = "/findByBoardId/{id}")
    public ResponseEntity<?> findByBoardId(@PathVariable("id") Long id) {
    	List<Port> portList = portRepository.findByBoardId(id);

        return ResponseEntity.status(HttpStatus.OK).body(portList);
    }  

    @Override
    public Port insert(@RequestBody Port port) {
        return super.insert(port);
    }        
	
    @Override
	public ResponseEntity<?> update(@RequestBody Port port, Errors errors) {
    	super.update(port, errors);

    	return ResponseEntity.status(HttpStatus.OK).body(port);
	} 	
}
