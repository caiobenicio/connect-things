package br.com.thing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Gateway;
import br.com.thing.repository.GatewayRepository;
import br.com.thing.utils.ServicePaths;

@RestController
@RequestMapping(path = ServicePaths.GATEWAY_PATH)
public class GatewayResource extends GenericService<Gateway, Long> {
	
    @Autowired
    private GatewayRepository gatewayRepository;
    
    
    public List<Gateway> findByClientId(Long clientId) {
    	return gatewayRepository.findByClientId(clientId);
    }

}
