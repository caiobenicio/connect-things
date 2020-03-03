package br.com.thing.entity;

<<<<<<< HEAD
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "room")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Room extends BaseEntity<Long> {

    private static final long serialVersionUID = 201602010251L;

    @NotNull
    @Column(name = "name", length = 120, nullable = false)
    private String name;
=======
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Room extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    private String nome;
    
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

	public Room(Long id, String nome, Home home) {
		super();
		this.id = id;
		this.nome = nome;
		this.home = home;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351

}
