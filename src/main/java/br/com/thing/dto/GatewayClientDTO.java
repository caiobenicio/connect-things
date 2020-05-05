package br.com.thing.dto;

import java.util.List;

import br.com.thing.entity.Gateway;

public class GatewayClientDTO {

	private String id;
	private String name;
	private String email;
	
	private List<Gateway> gateway;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public List<Gateway> getGateway() {
		return gateway;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGateway(List<Gateway> gateway) {
		this.gateway = gateway;
	}

	@Override
	public String toString() {
		return "GatewayClientDTO [id=" + id + ", name=" + name + ", email=" + email + ", gateway=" + gateway + "]";
	}
}
