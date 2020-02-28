package br.com.thing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {


}
