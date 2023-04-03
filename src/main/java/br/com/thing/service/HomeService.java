package br.com.thing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Client;
import br.com.thing.entity.Home;
import br.com.thing.repository.ClientRepository;
import br.com.thing.repository.HomeRepository;

@RestController
@RequestMapping(path = ServicePaths.HOME_PATH)
public class HomeService extends GenericService<Home, Long> {

    @Autowired
    private HomeRepository homeRepository;
    
    @Autowired
    private ClientRepository userRepository;
    
	@Override
	public Home insert(@RequestBody Home home) {
		home = super.insert(home);
		return home;
	}
	
    @GetMapping(value = "/findByClientId/{id}")
    public ResponseEntity<?> findByClientId(@PathVariable("id") Long id) {
        Optional<Client> client = userRepository.findById(id);
        Optional<List<Home>> home = homeRepository.findByClient(client.get());
        
        return ResponseEntity.status(HttpStatus.OK).body(home);
    }

}
