package br.com.thing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Client;
import br.com.thing.repository.ClientRepository;

@RestController
@RequestMapping(path = ServicePaths.CHANGE_PASSWORD_PATH)
public class ChangePasswordService extends GenericService<Client, Long> {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ClientRepository userRepository;
	
	@Override
	public ResponseEntity<?> update(@RequestBody Client user, Errors errors) {
		Client finduser = userRepository.findByEmail(user.getEmail());
		if (user.getPassword() != null && !user.getPassword().isEmpty()) 			
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		user.setId(finduser.getId());
		userRepository.updatePassword(user.getPassword(), finduser.getId());
		return ResponseEntity.status(HttpStatus.OK).body("Senha atualizada!");
	}
}
