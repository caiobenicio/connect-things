package br.com.thing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {

	List<Device> findByClientId(Long clientId);
}
