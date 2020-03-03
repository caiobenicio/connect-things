package br.com.thing.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD:src/main/java/br/com/thing/service/UserResource.java
import br.com.thing.entity.User;
=======
import br.com.thing.entity.Client;
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351:src/main/java/br/com/thing/resources/UserResource.java
import br.com.thing.utils.ResourcePaths;

@RestController
@RequestMapping(path = ResourcePaths.USER_PATH)
<<<<<<< HEAD:src/main/java/br/com/thing/service/UserResource.java
public class UserResource {
=======
public class UserResource extends GenericService<Client, Long> {
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351:src/main/java/br/com/thing/resources/UserResource.java

    @Autowired
    private PasswordEncoder passwordEncoder;

<<<<<<< HEAD:src/main/java/br/com/thing/service/UserResource.java
//    @Override
//    public User insert(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return super.insert(user);
//    }
=======
    @Override
    public Client insert(Client user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return super.insert(user);
    }
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351:src/main/java/br/com/thing/resources/UserResource.java

}
