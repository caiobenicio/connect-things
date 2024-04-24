package br.com.thing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.thing.enums.PortType;

@Entity
public class Port extends BaseEntity<Long> {

	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", length = 5)
	private PortType type;

	@JsonBackReference(value="board-port")		
	@ManyToOne
	@JoinColumn(name = "board_id")
	private Board board;
	
	@JsonBackReference		
	@OneToOne(mappedBy = "port")
    private Device device;	

	public Port() {
	}

	public Port(String name, PortType type, Board board) {
		this.name = name;
		this.type = type;
		this.board = board;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public PortType getType() {
		return type;
	}

	public void setType(PortType type) {
		this.type = type;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
		this.device.setPort(this);
	}
 
}
