package br.com.thing.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Device;

@RestController
@RequestMapping(path = ServicePaths.DISPOSITIVO_PATH)
public class DeviceResource extends GenericService<Device, Long> {


}
