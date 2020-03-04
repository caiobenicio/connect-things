package br.com.thing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.Home;

public interface HomeRepository extends JpaRepository<Home, Long> {

  //  List<ResidenciaEntity> findByQtdmorador(int qtdmorador);


}
