package br.com.thing.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}