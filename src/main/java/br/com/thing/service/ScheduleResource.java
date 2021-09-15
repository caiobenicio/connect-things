package br.com.thing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Schedule;
import br.com.thing.schedule.ScheduleTask;
import br.com.thing.utils.ServicePaths;

@RestController
@RequestMapping(path = ServicePaths.SCHEDULE_PATH)
public class ScheduleResource extends GenericService<Schedule, Long> {

    @Autowired
    private ScheduleTask agendador;

    @Override
    public Schedule insert(@RequestBody Schedule agenda) {
        agenda = super.insert(agenda);
        agendador.agendamento(agenda);
        return agenda;
    }

}
