package br.com.thing.schedule;

import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.thing.entity.ScheduleEntity;
import br.com.thing.repository.ScheduleRepository;
import br.com.thing.task.TaskExecutorEnd;
import br.com.thing.task.TaskExecutorStart;

@Component
public class ScheduleTask {
	
	private static ScheduleTask instance = new ScheduleTask();
	public static ScheduleTask getInstance() { return instance; }

	private static final Logger LOGGER = LogManager.getLogger(ScheduleTask.class);

	@Autowired
	private ScheduleRepository agendaRepository;

	private List<ScheduleEntity> agendamentos = new LinkedList<ScheduleEntity>();

	private Timer timer = new Timer();
	private Timer timer2 = new Timer();
	
	private int countInterval = 0;

	public void agendamento(ScheduleEntity agenda) {
		adicionarAgenda(agenda);
	}

	public void adicionarAgenda(ScheduleEntity agenda) {
		
		agendamentos.add(agenda);
		agendamentos.stream().sorted(Comparator.comparing(ScheduleEntity::getStartSchedule));

		ScheduleEntity first = agendamentos.remove(0);
		timer.schedule(new TaskExecutorStart(first, first.getCommand()), first.getStartSchedule());

		System.out.println("Agendamento inicial realizado para: " + first.getStartSchedule() + "|| Comando: "+ first.getCommand());
		
		if (first.getEndSchedule() != null) {
			
			timer2.schedule(new TaskExecutorEnd(first, first.getCommand().equalsIgnoreCase("L") ? "D" : "L"),
					first.getEndSchedule());
			
			System.out.println("Agendamento final realizado para: " + first.getEndSchedule());
		}

	}

	public void novoAgendamento(ScheduleEntity agenda) {

		if (agenda.isRepeat() && countInterval <= agenda.getInterval()) {
			Calendar c = Calendar.getInstance();
			c.setTime(agenda.getStartSchedule());
			c.add(Calendar.DATE, 1);
			agenda.setStartSchedule(c.getTime());

			c.setTime(agenda.getEndSchedule());
			c.add(Calendar.DATE, 1);
			agenda.setEndSchedule(c.getTime());

			timer.schedule(new TaskExecutorStart(agenda, agenda.getCommand()), agenda.getStartSchedule());
			timer2.schedule(new TaskExecutorEnd(agenda, agenda.getCommand().equalsIgnoreCase("L") ? "D" : "L"),
					agenda.getEndSchedule());
			
			countInterval++;

		} else {
			
			countInterval = 0;
		}

	}

}
