package br.com.thing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.ComodoEntity;

public interface ComodoRepository extends JpaRepository<ComodoEntity, Long> {


}
