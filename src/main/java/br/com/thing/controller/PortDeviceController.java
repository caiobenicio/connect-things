package br.com.thing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.PortDevice;
import br.com.thing.entity.PortDeviceKey;
import br.com.thing.repository.PortDeviceRepository;
import br.com.thing.service.ServicePaths;

@RestController
@RequestMapping(path = ServicePaths.PORT_DEVICE_PATH)
public class PortDeviceController {

    @Autowired
    private PortDeviceRepository portDeviceRepository;
    
	@PostMapping()
	public PortDeviceKey insert(@RequestBody PortDeviceKey portDeviceKey) {
		
		PortDevice portDevice = new PortDevice();
		portDevice.setId(portDeviceKey);
		
		portDeviceRepository.save(portDevice);

		return portDeviceKey;
	}
}
