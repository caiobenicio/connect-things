package br.com.thing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.Board;
import br.com.thing.entity.Client;

public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findByClientId(Long clientId);
	Optional<List<Board>> findByClient(Client client);
}
