package br.com.thing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.thing.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT a FROM Schedule as a WHERE (a.startSchedule >= now()) OR (a.repeat = true)")
    List<Schedule> buscarAgendasAbertas();

}
