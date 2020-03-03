package br.com.thing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    //public List<UserEntity> findByPermissoes(UserPermissionEntity permissao);
    //public UserEntity findByName(String name);
    public List<User>findByName(String name);



}
