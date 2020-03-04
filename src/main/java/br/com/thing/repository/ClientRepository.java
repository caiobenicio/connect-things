package br.com.thing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

    //public List<UserEntity> findByPermissoes(UserPermissionEntity permissao);
    //public UserEntity findByName(String name);
    public List<Client>findByName(String name);

}
