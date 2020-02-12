package br.com.thing.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.UserPermissionEntity;
import br.com.thing.security.UserPermissionKey;

public interface UserPermissionRepository extends JpaRepository<UserPermissionEntity, UserPermissionKey> {

}