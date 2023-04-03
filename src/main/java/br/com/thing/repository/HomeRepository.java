package br.com.thing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.Client;
import br.com.thing.entity.Home;

public interface HomeRepository extends JpaRepository<Home, Long> {

//    @Query(value = "SELECT h FROM Home as h WHERE h.client_id = :id")
//    Optional<Home> findByClientId(Long id);

    Optional<List<Home>> findByClient(Client client);

}
