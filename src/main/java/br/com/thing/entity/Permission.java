package br.com.thing.entity;

import javax.persistence.Entity;
<<<<<<< HEAD

@Entity
public class Permission extends BaseEntity<Long> {

    private static final long serialVersionUID = 201602010401L;

=======
import javax.persistence.Id;

@Entity
public class Permission extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;

	@Id
	private Long id;
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351
    private String role;

    public Permission() {
    }

<<<<<<< HEAD
    public String getRole() {
=======
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
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}