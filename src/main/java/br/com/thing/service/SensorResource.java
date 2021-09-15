package br.com.thing.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Sensor;
import br.com.thing.utils.ServicePaths;

@RestController
@RequestMapping(path = ServicePaths.SENSOR_PATH)
public class SensorResource extends GenericService<Sensor, Long> {


}
