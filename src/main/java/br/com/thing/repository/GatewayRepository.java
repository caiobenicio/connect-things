package br.com.thing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.Gateway;

public interface GatewayRepository extends JpaRepository<Gateway, Long> {

	List<Gateway> findByClientId(Long clientId);
}
