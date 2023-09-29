package br.com.thing.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Sensor extends BaseEntity<Long> {

    private String name;
    private String description;
    private Boolean active;
    
    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    
	public Sensor() {
		super();
	}

	public Sensor(String name, String description, Boolean active, Room room) {
		super();
		this.name = name;
		this.description = description;
		this.active = active;
		this.room = room;
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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}

