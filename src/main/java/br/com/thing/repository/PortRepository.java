package br.com.thing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.thing.entity.Port;

public interface PortRepository extends JpaRepository<Port, Long> {

	List<Port> findByBoardId(Long id); 
}
