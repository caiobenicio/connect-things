package br.com.thing.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Schedule extends BaseEntity<Long>{
	 private static final long serialVersionUID = 1L;

    private String topic;
    private String command;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone = "GMT-3")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_schedule", nullable = false)
    private Date dateSchedule;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_schedule", nullable = false)
    private Date startSchedule;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_schedule", nullable = true)
    private Date endSchedule;

    private boolean repeat;
    
    @Column(name = "interval", nullable = true)
    private Integer interval;

    public Schedule() {
    }

	public Schedule(String thingPath, String command, Date startSchedule, Date endSchedule, boolean repeat) {
		this.topic = thingPath;
		this.command = command;
		this.startSchedule = startSchedule;
		this.endSchedule = endSchedule;
		this.repeat = repeat;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Date getDateSchedule() {
		return dateSchedule;
	}

	public void setDateSchedule(Date dateSchedule) {
		this.dateSchedule = dateSchedule;
	}

	public Date getStartSchedule() {
		return startSchedule;
	}

	public void setStartSchedule(Date startSchedule) {
		this.startSchedule = startSchedule;
	}

	public Date getEndSchedule() {
		return endSchedule;
	}

	public void setEndSchedule(Date endSchedule) {
		this.endSchedule = endSchedule;
	}

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

}
