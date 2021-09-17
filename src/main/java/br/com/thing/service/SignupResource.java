package br.com.thing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Client;
import br.com.thing.entity.Permission;
import br.com.thing.repository.ClientRepository;


@RestController
@RequestMapping(path = ServicePaths.SIGNUP_PATH)
public class SignupResource extends GenericService<Client, Long> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientRepository userRepository;

    @Override    
    public Client insert(@RequestBody Client user) {

    	Client userBean = this.userRepository.findByEmail(user.getEmail());

        if (userBean != null) {
            throw new UsernameNotFoundException("Email "+user.getEmail()+". ja cadastrado");
        }

        List<Permission> list = new ArrayList<>();
        list.add(new Permission(2L, "ROLE_USER"));
        user.setPermissions(list);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        return super.insert(user); 
    }
    
}
