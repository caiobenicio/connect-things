package br.com.thing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.DispositivoEntity;

public interface DispositivoRepository extends JpaRepository<DispositivoEntity, Long> {


}
