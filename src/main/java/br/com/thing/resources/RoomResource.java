package br.com.thing.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Room;
import br.com.thing.utils.ResourcePaths;

@RestController
@RequestMapping(path = ResourcePaths.COMODO_PATH)
<<<<<<< HEAD:src/main/java/br/com/thing/service/ComodoResource.java
public class ComodoResource extends GenericService<Room, Long> {
=======
public class RoomResource extends GenericService<Room, Long> {
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351:src/main/java/br/com/thing/resources/RoomResource.java


}
