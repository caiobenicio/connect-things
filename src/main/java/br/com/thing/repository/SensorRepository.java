package br.com.thing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.SensorEntity;

public interface SensorRepository extends JpaRepository<SensorEntity, Long> {


}
