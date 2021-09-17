package br.com.thing.security;

import java.security.Principal;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.service.ServiceMap;
import br.com.thing.service.ServicePaths;

@RestController
@RequestMapping(ServicePaths.LOGIN_PATH)
@Transactional
public class SecurityService implements ServiceMap {

    @RequestMapping(method = { RequestMethod.GET })
    public Principal user(Principal user) {
        return user;
    }

}
