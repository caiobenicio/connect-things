package br.com.thing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.ResidenciaEntity;

import java.util.List;

public interface ResidenciaRepository extends JpaRepository<ResidenciaEntity, Long> {

  //  List<ResidenciaEntity> findByQtdmorador(int qtdmorador);






}
