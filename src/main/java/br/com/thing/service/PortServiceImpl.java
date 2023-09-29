package br.com.thing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thing.entity.Port;
import br.com.thing.repository.PortRepository;

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
