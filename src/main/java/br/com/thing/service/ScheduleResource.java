package br.com.thing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.ScheduleEntity;
import br.com.thing.schedule.ScheduleTask;
import br.com.thing.utils.ResourcePaths;

@RestController
@RequestMapping(path = ResourcePaths.SCHEDULE_PATH)
public class ScheduleResource extends GenericService<ScheduleEntity, Long> {

    @Autowired
    private ScheduleTask agendador;

    @Override
    public ScheduleEntity insert(@RequestBody ScheduleEntity agenda) {
        agenda = super.insert(agenda);
        agendador.agendamento(agenda);
        return agenda;
    }

}
