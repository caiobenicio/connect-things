package br.com.thing.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Device extends BaseEntity<Long> {

    private String name;
    private String iconPath;
    private String description;
    private Boolean active;
    
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
    
    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    
	public Device() {
		super();
	}

	public Device(String name, String description, Boolean active, Client client, Room room) {
		super();
		this.name = name;
		this.description = description;
		this.active = active;
		this.client = client;
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
   	
}
