package br.com.thing.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.ComodoEntity;
import br.com.thing.utils.ResourcePaths;

@RestController
@RequestMapping(path = ResourcePaths.COMODO_PATH)
public class ComodoResource extends GenericService<ComodoEntity, Long> {


}
