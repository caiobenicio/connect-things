package br.com.thing.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Permission;
import br.com.thing.utils.ResourcePaths;

@RestController
@RequestMapping(path = ResourcePaths.PERMISSION_PATH)
public class PermissionService extends GenericService<Permission, Long> {

}
