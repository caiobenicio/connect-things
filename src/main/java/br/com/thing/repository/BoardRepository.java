package br.com.thing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findByClientId(Long clientId);
}
