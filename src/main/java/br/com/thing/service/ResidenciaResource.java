package br.com.thing.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Home;
import br.com.thing.utils.ResourcePaths;

@RestController
@RequestMapping(path = ResourcePaths.RESIDENCIA_PATH)
public class ResidenciaResource extends GenericService<Home, Long> {


}
