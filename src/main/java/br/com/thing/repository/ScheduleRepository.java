package br.com.thing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.thing.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT a FROM Schedule as a WHERE (a.startSchedule >= now()) OR (a.repeat = true)")
    List<Schedule> buscarAgendasAbertas();
    
    // HOJE
    @Query("SELECT a.id FROM Schedule as a WHERE (a.dateSchedule = CURRENT_DATE OR a.repeat = true) AND client_id = :clientId")
    List<Long> buscarAgendasAbertas(Long clientId);

    // HOJE PRA FRENTE NAO EXECUTADO
    @Query("SELECT a.id FROM Schedule as a WHERE (a.dateSchedule >= CURRENT_DATE OR a.repeat = true) AND client_id = :clientId and executed = false")
    List<Long> findByScheduled(Long clientId);

    // TODOS
    @Query("SELECT a.id FROM Schedule as a WHERE client_id = :clientId")
    List<Long> findByAllScheduleNotEx(Long clientId);

    @Query("SELECT a FROM Schedule as a WHERE a.dateSchedule = TO_DATE(:date, 'YYYY-MM-DD') and client_id = :clientId")
    Optional<List<Schedule>> findByDateSchedule(Long clientId, String date);
}
