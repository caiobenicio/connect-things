package br.com.thing.entity;

import java.util.Date;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Schedule extends BaseEntity<Long> {

	@NotNull
	private String topic;

	@NotNull
	private String command;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT-3")
	@Temporal(TemporalType.DATE)
	@Column(name = "date_schedule", nullable = true)
	private Date dateSchedule;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_schedule", nullable = false)
	private Date startSchedule;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_schedule", nullable = true)
	private Date endSchedule;

	private boolean repeat;
	private Integer interval;

	@JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")	
	@Cascade(CascadeType.SAVE_UPDATE)
    public Client client;	

	@JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id")	
	@Cascade(CascadeType.SAVE_UPDATE)
    public Device device;	

	private boolean executed;	

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

	public void setClient(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public boolean isExecuted() {
		return executed;
	}

	public void setExecuted(boolean executed) {
		this.executed = executed;
	}	
	
}
