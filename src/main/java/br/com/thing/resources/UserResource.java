package br.com.thing.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Client;
import br.com.thing.utils.ResourcePaths;

@RestController
@RequestMapping(path = ResourcePaths.USER_PATH)
public class UserResource extends GenericService<Client, Long> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Client insert(Client user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return super.insert(user);
    }

}
