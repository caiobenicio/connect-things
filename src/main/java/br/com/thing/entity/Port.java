package br.com.thing.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import br.com.thing.enums.PortType;

@Entity
public class Port extends BaseEntity<Long>{

	private String port;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type", length = 5)
	private PortType type;
	
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
    
    @OneToOne(mappedBy = "port", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn   
    private Device device;  
    
    @OneToOne(mappedBy = "port", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn   
    private Sensor sensor;    

	public String getPort() {
		return port;
	}

	public PortType getType() {
		return type;
	}

	public void setPort(String port) {
		this.port = port;
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
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

}
