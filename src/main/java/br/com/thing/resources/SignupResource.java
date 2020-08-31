package br.com.thing.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Client;
import br.com.thing.entity.Permission;
import br.com.thing.repository.ClientRepository;
import br.com.thing.utils.ResourcePaths;


@RestController
@RequestMapping(path = ResourcePaths.SIGNUP_PATH)
public class SignupResource extends GenericService<Client, Long> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientRepository userRepository;

    @Override    
    public Client insert(@RequestBody Client user) {

    	Client userBean = this.userRepository.findByEmail(user.getEmail());

        if (userBean != null) {
            System.out.println("Email ja cadastrado");
            return null;
         //   throw new UsernameNotFoundException("User with email \"" + email + "\" was not found");
        }

    	Permission p = new Permission(2L, "ROLE_USER");
        List<Permission> list = new ArrayList<>();
        list.add(p);
        user.setPermissions(list);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsGateway(true);

        return super.insert(user); 
    }
}
