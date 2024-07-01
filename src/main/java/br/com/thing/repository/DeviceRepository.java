package br.com.thing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.thing.dto.DeviceDTO;
import br.com.thing.entity.Device;

@Transactional
public interface DeviceRepository extends JpaRepository<Device, Long> {

	// @Query("SELECT a FROM Device as d WHERE d.client_id = ?1")
	List<Device> findByClientId(Long clientId);

	@Modifying(clearAutomatically = true)
	@Query("update Device set port_id = :portId where id = :id")
	void updatePort(@Param("portId") Long portId, @Param("id") Long id);

   @Query(nativeQuery = true, value = "SELECT p.id as portId, p.name as portName FROM Port p inner join Device d on p.id = d.port_id where p.id = :id")
   DeviceDTO getDeviceBoardByPort(@Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query("update Device set active = :status where id = :id")
	void updateStatus(Long id, boolean status);

	// @Query("update Device set port_id = :portId where id = :id")
	// void updatePort(@Param("portId") Long portId, @Param("id") Long id);  
		
}
