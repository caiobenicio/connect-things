package br.com.thing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {

	//@Query("SELECT a FROM Device as d WHERE d.client_id = ?1")
	List<Device> findByClientId(Long clientId);
}
