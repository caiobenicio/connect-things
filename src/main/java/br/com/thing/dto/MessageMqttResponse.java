package br.com.thing.dto;

public class MessageMqttResponse {
	private Long user;
	private Long board;
	private String msgType;
	private String pinsIn[];
	private String pinsOut[];
	private int status;
	private String pin;	
	
	public MessageMqttResponse() {
	}

	public Long getUser() {
		return user;
	}

	public String getMsgType() {
		return msgType;
	}

	public String[] getPinsIn() {
		return pinsIn;
	}

	public String[] getPinsOut() {
		return pinsOut;
	}

	public int getStatus() {
		return status;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public void setPinsIn(String[] pinsIn) {
		this.pinsIn = pinsIn;
	}

	public void setPinsOut(String[] pinsOut) {
		this.pinsOut = pinsOut;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Long getBoard() {
		return board;
	}

	public void setBoard(Long board) {
		this.board = board;
	}

}
