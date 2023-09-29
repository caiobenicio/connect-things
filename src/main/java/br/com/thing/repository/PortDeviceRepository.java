package br.com.thing.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.PortDevice;
import br.com.thing.entity.PortDeviceKey;

public interface PortDeviceRepository extends JpaRepository<PortDevice, PortDeviceKey> {

}