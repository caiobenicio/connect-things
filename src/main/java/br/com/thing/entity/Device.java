package br.com.thing.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "device")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Device extends BaseEntity<Long> {

    private static final long serialVersionUID = 201602010251L;

    @NotNull
    @Size(min = 2, max = 45)
    @Column(name = "name", length = 45, nullable = false)
    private String name;
    
    @Size(min = 2, max = 45)
    @Column(name = "function", length = 45, nullable = true)
    private String function;

    @NotNull
    @Column(name = "active", length = 255, nullable = false)
    private boolean active;

	public Device() {
		super();
	}

	public Device(String name, String function, Boolean active) {
		super();
		this.name = name;
		this.function = function;
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
