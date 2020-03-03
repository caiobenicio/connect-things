package br.com.thing.repository;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import br.com.thing.entity.UserPermission;
import br.com.thing.entity.UserPermissionKey;

public interface UserPermissionRepository extends JpaRepository<UserPermission, UserPermissionKey> {
=======
import br.com.thing.entity.ClientPermission;
import br.com.thing.entity.ClientPermissionKey;

public interface UserPermissionRepository extends JpaRepository<ClientPermission, ClientPermissionKey> {
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351

}