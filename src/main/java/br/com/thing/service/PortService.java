package br.com.thing.service;

import java.util.List;

import br.com.thing.entity.Port;

public interface PortService {

	Port save(Port port);
	List<Port> findByBoardId(Long id);
}
