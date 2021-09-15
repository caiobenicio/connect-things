package br.com.thing.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Permission;
import br.com.thing.utils.ServicePaths;

@RestController
@RequestMapping(path = ServicePaths.PERMISSION_PATH)
public class PermissionService extends GenericService<Permission, Long> {

}
