package br.com.thing.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Gateway extends BaseEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String model;

	@Column(name = "quantity_doors_io", length = 11, nullable = false)
	private Integer quantityDoorsIO;

	@Column(name = "input_tension", length = 11, nullable = false)
	private Double inputTesion;

	@ElementCollection
	@CollectionTable(name = "connectivity_types")
	private Set<String> connectivityTypes = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	
	public Gateway() {
		super();
	}
	
	public Gateway(String name, String model, Integer quantityDoorsIO, Double inputTesion,
			Set<String> connectivityTypes, Client client) {
		super();
		this.name = name;
		this.model = model;
		this.quantityDoorsIO = quantityDoorsIO;
		this.inputTesion = inputTesion;
		this.connectivityTypes = connectivityTypes;
		this.client = client;
	}
	
	public Gateway(Long id, String name, String model, Integer quantityDoorsIO, Double inputTesion,
			Set<String> connectivityTypes, Client client) {
		super();
		this.id = id;
		this.name = name;
		this.model = model;
		this.quantityDoorsIO = quantityDoorsIO;
		this.inputTesion = inputTesion;
		this.connectivityTypes = connectivityTypes;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getModel() {
		return model;
	}

	public Integer getQuantityDoorsIO() {
		return quantityDoorsIO;
	}

	public Double getInputTesion() {
		return inputTesion;
	}

	public Set<String> getConnectivityTypes() {
		return connectivityTypes;
	}

	public Client getClient() {
		return client;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setQuantityDoorsIO(Integer quantityDoorsIO) {
		this.quantityDoorsIO = quantityDoorsIO;
	}

	public void setInputTesion(Double inputTesion) {
		this.inputTesion = inputTesion;
	}

	public void setConnectivityTypes(Set<String> connectivityTypes) {
		this.connectivityTypes = connectivityTypes;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
