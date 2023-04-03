package br.com.thing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Board extends BaseEntity<Long> {

    private String name;
    private String model;

//	@Column(name = "topic_base", length = 20)
//	private String topicBase;

    @Column(name = "topic_subscribe", length = 40)
    private String topicSubscribe;

    @Column(name = "topic_publish", length = 40)
    private String topicPublish;

    @Column(name = "quantity_doors_io", length = 11, nullable = true)
    private Integer quantityDoorsIO;

    @Column(name = "input_tension", length = 11, nullable = true)
    private Double inputTesion;

    // @ElementCollection
    // @CollectionTable(name = "connectivity_types")
    // private Set<String> connectivityTypes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Board() {
        super();
    }

    public Board(String name, String model, Integer quantityDoorsIO, Double inputTesion, Client client) {
        super();
        this.name = name;
        this.model = model;
        this.quantityDoorsIO = quantityDoorsIO;
        this.inputTesion = inputTesion;
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

    public Integer getQuantityDoorsIO() {
        return quantityDoorsIO;
    }

    public Double getInputTesion() {
        return inputTesion;
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

    public void setQuantityDoorsIO(Integer quantityDoorsIO) {
        this.quantityDoorsIO = quantityDoorsIO;
    }

    public void setInputTesion(Double inputTesion) {
        this.inputTesion = inputTesion;
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
}
