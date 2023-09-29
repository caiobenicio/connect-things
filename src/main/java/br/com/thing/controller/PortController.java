package br.com.thing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Port;
import br.com.thing.service.PortService;
import br.com.thing.service.ServicePaths;

@RestController
@RequestMapping(path = ServicePaths.PORT_PATH)
public class PortController {

	@Autowired
	private PortService portService;

	@PostMapping()
	public List<Port> insertAll(@RequestBody List<Port> ports) {
		ports.stream().forEach(a -> {
			a = portService.save(a);
		});

		return ports;
	}
	
	@GetMapping(value = "/findByBoardId/{id}")
    public ResponseEntity<?> findByBoardId(@PathVariable("id") Long id) {
    	List<Port> portList = portService.findByBoardId(id);

        return ResponseEntity.status(HttpStatus.OK).body(portList);
    }
}