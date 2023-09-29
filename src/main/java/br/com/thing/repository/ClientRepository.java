package br.com.thing.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.thing.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

    //public List<UserEntity> findByPermissoes(UserPermissionEntity permissao);
    //public UserEntity findByName(String name);
    public List<Client>findByName(String name);
    
    @Transactional
    @Modifying
    @Query("update Client c set c.password = ?1 where c.id = ?2")
    void updatePassword(String pass, Long id);
}
