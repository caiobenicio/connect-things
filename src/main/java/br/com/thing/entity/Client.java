package br.com.thing.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Client extends BaseEntity<Long> {
	
	private String name;
	private String email;
	private String password;

	@JsonIgnore
	@OneToMany(mappedBy = "client", orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.FALSE)
	//@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)    
	@OrderBy("name ASC")
	private List<Home> homes= new ArrayList<>();

	@JsonIgnore
	@JsonManagedReference
	@OneToMany(mappedBy = "client")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Board> boards = new ArrayList<>();

	@JsonIgnore
	@JsonManagedReference
	@OneToMany(mappedBy = "client")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Device> devices = new ArrayList<>();	

    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "client_permission", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private List<Permission> permissions;

	@JsonBackReference		
	@OneToOne(mappedBy = "client", orphanRemoval = true, cascade = CascadeType.ALL)
    private Schedule schedule;	

	public Client() {
	}

	public Client(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Client(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<Board> getBoards() {
        return boards;
    }

    public void setBoard(List<Board> boards) {
        this.boards = boards;
    }

    public List<Home> getHomes() {
        return homes;
    }

    public void setHomes(List<Home> homes) {
        this.homes = homes;
    }

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
		
}
