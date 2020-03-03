package br.com.thing.entity;

import javax.persistence.Entity;

@Entity
public class Permission extends BaseEntity<Long> {

    private static final long serialVersionUID = 201602010401L;

    private String role;

    public Permission() {
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}