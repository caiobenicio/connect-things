package br.com.thing.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Device;
import br.com.thing.utils.ResourcePaths;

@RestController
@RequestMapping(path = ResourcePaths.DISPOSITIVO_PATH)
<<<<<<< HEAD:src/main/java/br/com/thing/service/DispositivoResource.java
public class DispositivoResource extends GenericService<Device, Long> {
=======
public class DeviceResource extends GenericService<Device, Long> {
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351:src/main/java/br/com/thing/resources/DeviceResource.java


}
