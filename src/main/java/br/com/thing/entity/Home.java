package br.com.thing.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Home extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    private String name;

	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;

	@OneToMany(mappedBy="home")
	private List<Room> room;
	
	
	public Home() {
		super();
	}

	public Home(Long id, String name, Client client, List<Room> room) {
		super();
		this.id = id;
		this.name = name;
		this.client = client;
		this.room = room;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Room> getRoom() {
		return room;
	}

	public void setRoom(List<Room> room) {
		this.room = room;
	}

}
