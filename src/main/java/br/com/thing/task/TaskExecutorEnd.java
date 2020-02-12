package br.com.thing.task;

import java.util.TimerTask;

import br.com.thing.entity.ScheduleEntity;
import br.com.thing.mqtt.Publisher;

public class TaskExecutorEnd extends TimerTask {

	private String command;
	private ScheduleEntity schedule;

	public TaskExecutorEnd(ScheduleEntity schedule, String command) {
		this.schedule = schedule;
		this.command = command;
	}

	@Override
	public void run() {

		Publisher p = new Publisher();
		p.publishOnTopic(schedule.getTopic(), command);
	}
}
