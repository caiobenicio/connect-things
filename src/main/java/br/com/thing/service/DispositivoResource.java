package br.com.thing.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Device;
import br.com.thing.utils.ResourcePaths;

@RestController
@RequestMapping(path = ResourcePaths.DISPOSITIVO_PATH)
public class DispositivoResource extends GenericService<Device, Long> {


}
