package br.com.thing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.thing.entity.ScheduleEntity;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    @Query("SELECT a FROM ScheduleEntity as a WHERE (a.startSchedule >= now()) OR (a.repeat = true)")
    List<ScheduleEntity> buscarAgendasAbertas();

}
