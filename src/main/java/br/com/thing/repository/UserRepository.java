package br.com.thing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import br.com.thing.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    //public List<UserEntity> findByPermissoes(UserPermissionEntity permissao);
    //public UserEntity findByName(String name);
    public List<User>findByName(String name);
=======
import br.com.thing.entity.Client;

import java.util.List;

public interface UserRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

    //public List<UserEntity> findByPermissoes(UserPermissionEntity permissao);
    //public UserEntity findByName(String name);
    public List<Client>findByName(String name);
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351



}
