package br.com.thing.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sensor")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Sensor extends BaseEntity<Long> {

    private static final long serialVersionUID = 201602010251L;

    @NotNull
    @Size(min = 4, max = 45)
    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", length = 255, nullable = true)
    private String description;

    @NotNull
    @Column(name = "active", length = 10, nullable = false)
    private boolean active;

    public Sensor() {
    }

	public Sensor(String name, String description, boolean active) {
		super();
		this.name = name;
		this.description = description;
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}

