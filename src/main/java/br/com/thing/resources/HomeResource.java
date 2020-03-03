package br.com.thing.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Home;
import br.com.thing.utils.ResourcePaths;

@RestController
@RequestMapping(path = ResourcePaths.RESIDENCIA_PATH)
<<<<<<< HEAD:src/main/java/br/com/thing/service/ResidenciaResource.java
public class ResidenciaResource extends GenericService<Home, Long> {
=======
public class HomeResource extends GenericService<Home, Long> {
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351:src/main/java/br/com/thing/resources/HomeResource.java


}
