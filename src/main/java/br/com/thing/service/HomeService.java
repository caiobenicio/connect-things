package br.com.thing.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Home;
import br.com.thing.utils.ServicePaths;

@RestController
@RequestMapping(path = ServicePaths.HOME_PATH)
public class HomeService extends GenericService<Home, Long> {

	@Override
	public Home insert(@RequestBody Home home) {
		home = super.insert(home);
		return home;
	}
}
