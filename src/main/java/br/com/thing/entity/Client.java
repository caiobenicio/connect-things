package br.com.thing.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    private String name;
	private String email;
	private String password;
	
	@Column(name = "gateway")
	private Boolean isGateway;

	@JsonIgnore
	@OneToMany(mappedBy="client")
	private List<Home> home = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="client")
	private List<Gateway> gateway = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "client_permission", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "permission_id") )
	private List<Permission> permissions;


	public Client() {
	}

    public Client(String name, String email, String password, Boolean isGateway) {
    	super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.isGateway = isGateway;
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

	public Boolean isGateway() {
		return isGateway;
	}

	public void setIsGateway(Boolean isGateway) {
		this.isGateway = isGateway;
	}
}
