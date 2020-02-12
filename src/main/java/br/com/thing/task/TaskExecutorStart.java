package br.com.thing.task;

import java.util.TimerTask;

import br.com.thing.entity.ScheduleEntity;
import br.com.thing.mqtt.Publisher;
import br.com.thing.schedule.ScheduleTask;

public class TaskExecutorStart extends TimerTask {

	private String command;
	private ScheduleEntity schedule;

	public TaskExecutorStart(ScheduleEntity schedule, String command) {
		this.schedule = schedule;
		this.command = command;
	}

	@Override
	public void run() {

		Publisher p = new Publisher();
		p.publishOnTopic(schedule.getTopic(), command);

		ScheduleTask s = new ScheduleTask();
		s.novoAgendamento(schedule);
	}
}