package br.com.thing.entity;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD

@Entity
public class Home extends BaseEntity<Long> {

    private static final long serialVersionUID = 201602010251L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;

//	@JsonIgnore
//	@ManyToOne
//	@JoinColumn(name="user_id")
//    private User user;
    
    public Home() {
    }

	public Home(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

=======
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

	
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351
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

<<<<<<< HEAD

=======
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
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351

}
