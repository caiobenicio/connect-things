package br.com.thing.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Room extends BaseEntity<Long> {

    private String name;
    
    @ManyToOne
    @JoinColumn(name="home_id")
    private Home home;

	@OneToMany(mappedBy="room")
	private List<Sensor> sensor;
	
	@OneToMany(mappedBy="room")
	private List<Device> device;
	
	public Room() {
		super();
	}

	public Room(String name, Home home) {
		super();
		this.name = name;
		this.home = home;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}

}
