package br.com.thing.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.ClientPermission;
import br.com.thing.entity.ClientPermissionKey;

public interface UserPermissionRepository extends JpaRepository<ClientPermission, ClientPermissionKey> {

}