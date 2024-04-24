package br.com.thing.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.thing.enums.DeviceType;

@Entity
public class Board extends BaseEntity<Long> {

	private String name;
	private String model;

	@Column(name = "topic_subscribe", length = 40)
	private String topicSubscribe;

	@Column(name = "topic_publish", length = 40)
	private String topicPublish;

	@JsonBackReference	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", length = 10)
	private DeviceType type;

	private boolean status;
	
	@JsonManagedReference(value="board-port")	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Port> ports;

    //	private String serialNumber;
    //	private String lastActivity;
	//  private Set<String> connectivityTypes = new HashSet<>();
	//  private Double inputTesion;
	//  private Integer quantityDoorsIO;

	public Board() {}

	public Board(String name, String model, Client client) {
		this.name = name;
		this.model = model;
		this.client = client;
	}

	public Long getId() {
		return super.getId();
	}

	public String getName() {
		return name;
	}

	public String getModel() {
		return model;
	}

	public Client getClient() {
		return client;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getTopicSubscribe() {
		return topicSubscribe;
	}

	public void setTopicSubscribe(String topicSubscribe) {
		this.topicSubscribe = topicSubscribe;
	}

	public String getTopicPublish() {
		return topicPublish;
	}

	public void setTopicPublish(String topicPublish) {
		this.topicPublish = topicPublish;
	}

	public DeviceType getType() {
		return type;
	}

	public void setType(DeviceType deviceType) {
		this.type = deviceType;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Port> getPorts() {
		if (this.ports == null) {
			this.ports = new ArrayList<>();
		}
		return ports;
	}

	public void setPorts(List<Port> ports) {
		if (this.ports == null) {
			this.ports = ports;
		} else {
	        this.ports.addAll(ports);
	    }		
	}
}
