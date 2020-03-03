package br.com.thing.entity;

<<<<<<< HEAD
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sensor")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Sensor extends BaseEntity<Long> {

    private static final long serialVersionUID = 201602010251L;

    @NotNull
    @Size(min = 4, max = 45)
    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", length = 255, nullable = true)
    private String description;

    @NotNull
    @Column(name = "active", length = 10, nullable = false)
    private boolean active;

    public Sensor() {
    }

	public Sensor(String name, String description, boolean active) {
		super();
		this.name = name;
		this.description = description;
		this.active = active;
=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Sensor extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    private String name;
    private String description;
    private Boolean active;
    
    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    
	public Sensor() {
		super();
	}

	public Sensor(Long id, String name, String description, Boolean active, Room room) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.active = active;
		this.room = room;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351
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

<<<<<<< HEAD
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

=======
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

>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351
}

