package br.com.thing.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Client;
import br.com.thing.entity.Permission;
import br.com.thing.model.Message;
import br.com.thing.repository.ClientRepository;

@RestController
@RequestMapping(path = ServicePaths.USER_PATH)
public class UserService extends GenericService<Client, Long> {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ClientRepository userRepository;
	
	Message message = new Message();

	public Client findByEmail(Client user) {
		return user;
	}

	@Override
	public Client insert(@RequestBody Client user) {
		
        List<Permission> list = new ArrayList<>();
        list.add(new Permission(2L, "ROLE_USER"));
        user.setPermissions(list);
        
        if (!user.getPassword().isEmpty()) 	
        	user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		return super.insert(user);
	}
	
	@Override
	public ResponseEntity<?> update(@RequestBody Client user, Errors errors) {
		
		if (user.getPassword() != null && !user.getPassword().isEmpty()) 			
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		return super.update(user, errors);
	}

	@Transactional
	@RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {

		Optional<Client> client = userRepository.findById(id);
		client.get().setPassword(null);
		return ResponseEntity.status(HttpStatus.OK).body(client);
	}
	
}
