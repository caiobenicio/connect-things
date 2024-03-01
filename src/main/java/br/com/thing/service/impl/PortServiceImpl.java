package br.com.thing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thing.entity.Port;
import br.com.thing.repository.PortRepository;
import br.com.thing.service.PortService;

@Service
public class PortServiceImpl implements PortService {

	@Autowired
	private PortRepository portRepository;

	@Override
	public Port save(Port port) {
		return portRepository.save(port);
	}

	@Override
	public List<Port> findByBoardId(Long id) {
		return portRepository.findByBoardId(id);
	}

}
