package br.com.thing.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Sensor;
import br.com.thing.utils.ResourcePaths;

@RestController
@RequestMapping(path = ResourcePaths.SENSOR_PATH)
public class SensorResource extends GenericService<Sensor, Long> {


}
