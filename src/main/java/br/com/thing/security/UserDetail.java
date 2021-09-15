package br.com.thing.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.thing.entity.Client;
import br.com.thing.entity.Permission;
import br.com.thing.repository.ClientRepository;

@Component
public class UserDetail implements UserDetailsService {

    @Autowired
    private ClientRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client user = this.userRepository.findByEmail(email);

        if (user == null) 
            throw new UsernameNotFoundException("User with email \"" + email + "\" was not found");

        LoginDetailBean login = new LoginDetailBean(user.getId(), user.getName(), user.getEmail(), user.getPassword());

        for (Permission permission : user.getPermissions()) {
            login.addRole(permission.getRole());
        }
        
//        if (user.getHomes()!= null && !user.getHomes().isEmpty()) {        	
//        	for (Home home : user.getHomes()) {
//        		login.set(home.getName());
//        	}
//        }
        
        return login;
    }

}