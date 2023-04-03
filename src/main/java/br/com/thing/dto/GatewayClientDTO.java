package br.com.thing.dto;

import java.util.List;

import br.com.thing.entity.Board;

public class GatewayClientDTO {

	private String id;
	private String name;
	private String email;
	
	private List<Board> board;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public List<Board> getBoard() {
		return board;
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

	public void setBoard(List<Board> board) {
		this.board = board;
	}

	@Override
	public String toString() {
		return "GatewayClientDTO [id=" + id + ", name=" + name + ", email=" + email + ", board=" + board + "]";
	}
}
