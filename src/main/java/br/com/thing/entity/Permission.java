package br.com.thing.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Permission extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;

	@Id
	private Long id;
    private String role;

    public Permission() {
    }

    public Permission(Long id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}