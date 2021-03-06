package br.com.thing.dto;

public class OutputMessageSensorDTO {

	private Long id;
	private String name;
	private String topic;
	private String mensage;
	
	
	public OutputMessageSensorDTO(Long id, String name, String topic, String mensage) {
		super();
		this.id = id;
		this.name = name;
		this.topic = topic;
		this.mensage = mensage;
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


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public String getMensage() {
		return mensage;
	}


	public void setMensage(String mensage) {
		this.mensage = mensage;
	}
	
}
