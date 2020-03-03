package br.com.thing.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.thing.entity.Permission;
<<<<<<< HEAD
import br.com.thing.entity.User;
=======
import br.com.thing.entity.Client;
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351
import br.com.thing.repository.UserRepository;

@Component
public class UserDetail implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
<<<<<<< HEAD
        User user = this.userRepository.findByEmail(email);
=======
        Client user = this.userRepository.findByEmail(email);
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351

        if (user == null) {
            throw new UsernameNotFoundException("User with email \"" + email + "\" was not found");
        }

        LoginDetailBean login = new LoginDetailBean(user.getName(), user.getEmail(), user.getPassword());

<<<<<<< HEAD
//        for (Permission permission : user.getPermissions()) {
//            login.addRole(permission.getRole());
//        }
=======
        for (Permission permission : user.getPermissions()) {
            login.addRole(permission.getRole());
        }
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351

        return login;
    }

}