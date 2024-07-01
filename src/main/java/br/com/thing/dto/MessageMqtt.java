package br.com.thing.dto;

public class MessageMqtt {
	private Long user;
	private Long board;
	private String msgType;
	private Integer pin;
	private Integer action;
	
	public MessageMqtt() {
	}

	public MessageMqtt(Long user, Integer pin, String msgType) {
		super();
		this.user = user;
		this.pin = pin;
		this.msgType = msgType;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}
	
	public Long getBoard() {
		return board;
	}

	public void setBoard(Long board) {
		this.board = board;
	}
}
