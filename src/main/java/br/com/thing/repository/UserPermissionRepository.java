package br.com.thing.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.UserPermission;
import br.com.thing.entity.UserPermissionKey;

public interface UserPermissionRepository extends JpaRepository<UserPermission, UserPermissionKey> {

}