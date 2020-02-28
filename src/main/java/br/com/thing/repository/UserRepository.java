package br.com.thing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.Client;

import java.util.List;

public interface UserRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

    //public List<UserEntity> findByPermissoes(UserPermissionEntity permissao);
    //public UserEntity findByName(String name);
    public List<Client>findByName(String name);



}
