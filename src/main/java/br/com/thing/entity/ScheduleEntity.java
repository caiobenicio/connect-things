package br.com.thing.entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_schedule")
@AttributeOverride(name = "id", column = @Column(name = "id_schedule"))
public class ScheduleEntity extends BaseEntity<Long>{

	 private static final long serialVersionUID = 201602010251L;

    @NotNull
    @Column(name = "topic", length = 45, nullable = false)
    private String topic;

    @NotNull
    @Column(name = "command", length = 6, nullable = false)
    private String command;
    
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone = "GMT-3")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_schedule", nullable = false)
    private Date dateSchedule;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_schedule", nullable = false)
    private Date startSchedule;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_schedule", nullable = true)
    private Date endSchedule;

    @NotNull
    @Column(name = "repeat", nullable = false)
    private boolean repeat;
    
    @Column(name = "interval", nullable = true)
    private Integer interval;

    public ScheduleEntity() {
    }

	public ScheduleEntity(String thingPath, String command, Date startSchedule, Date endSchedule, boolean repeat) {
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
