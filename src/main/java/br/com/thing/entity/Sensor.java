package br.com.thing.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Sensor extends BaseEntity<Long> {

    private String name;
    private String description;
    private Boolean active;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "port_id")
    public Port port;
    
//    @ManyToOne
//    @JoinColumn(name="room_id")
//    private Room room;
    
	public Sensor() {
		super();
	}

	public Sensor(String name, String description, Boolean active) {
		super();
		this.name = name;
		this.description = description;
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Port getPort() {
		return port;
	}

	public void setPort(Port port) {
		this.port = port;
	}

}