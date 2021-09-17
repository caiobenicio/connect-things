package br.com.thing.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Room;

@RestController
@RequestMapping(path = ServicePaths.COMODO_PATH)
public class RoomResource extends GenericService<Room, Long> {


}
