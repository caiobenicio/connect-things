package br.com.thing.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Device extends BaseEntity<Long> {

    private String name;
    private String iconPath;
    private String description;
    private Boolean active;
         
	@JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "port_id")	
    public Port port;

	@JsonBackReference	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;	
  
	public Device() {}

	public Device(String name, String description, Boolean active) {
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

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public Port getPort() {
		return port;
	}

	public void setPort(Port port) {
		this.port = port;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
   	
}
