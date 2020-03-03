package br.com.thing.task;

import java.util.TimerTask;

import br.com.thing.entity.Schedule;
import br.com.thing.mqtt.Publisher;

public class TaskExecutorEnd extends TimerTask {

	private String command;
	private Schedule schedule;

	public TaskExecutorEnd(Schedule schedule, String command) {
		this.schedule = schedule;
		this.command = command;
	}

	@Override
	public void run() {

		Publisher p = new Publisher();
		p.publishOnTopic(schedule.getTopic(), command);
	}
}
