package br.com.thing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.User;
import br.com.thing.utils.ResourcePaths;

@RestController
@RequestMapping(path = ResourcePaths.USER_PATH)
public class UserResource {

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public User insert(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return super.insert(user);
//    }

}
