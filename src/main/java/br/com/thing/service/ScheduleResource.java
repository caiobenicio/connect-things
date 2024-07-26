package br.com.thing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Home;
import br.com.thing.entity.Schedule;
import br.com.thing.repository.ScheduleRepository;
import br.com.thing.schedule.ScheduleTask;

@RestController
@RequestMapping(path = ServicePaths.SCHEDULE_PATH)
public class ScheduleResource extends GenericService<Schedule, Long> {

    @Autowired
    private ScheduleTask agendador;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Schedule insert(@RequestBody Schedule agenda) {
        agenda = super.insert(agenda);
        agendador.agendamento(agenda);
        return agenda;
    }

    @GetMapping(value = "/searchOpenSchedule/{clientId}")
    public ResponseEntity<?> searchOpenSchedule(@PathVariable("clientId") Long clientId) {
        List<Long> schedule = scheduleRepository.buscarAgendasAbertas(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(schedule);
    }

    @GetMapping(value = "/findByScheduled/{clientId}")
    public ResponseEntity<?> findByScheduled(@PathVariable("clientId") Long clientId) {        
        List<Long> schedule = scheduleRepository.findByScheduled(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(schedule);
    }    

    @GetMapping(value = "/findByAllScheduleNotEx/{clientId}")
    public ResponseEntity<?> findByAllScheduleNotEx(@PathVariable("clientId") Long clientId) {        
        List<Long> schedule = scheduleRepository.findByAllScheduleNotEx(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(schedule);
    }     
    
    @GetMapping(value = "/findByDateSchedule/{clientId}/{date}")
    public ResponseEntity<?> findByDateSchedule(@PathVariable("clientId") Long clientId, @PathVariable("date") String date) {
        Optional<List<Schedule>> schedule = scheduleRepository.findByDateSchedule(clientId, date);
        if (schedule.isPresent()) {
            if (schedule.get() != null) {
                for (Schedule item : schedule.get()) {
                    if (item.getClient() != null) {
                        item.setClient(null);
                    }
                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(schedule);
    }    
}
