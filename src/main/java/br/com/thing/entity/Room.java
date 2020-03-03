package br.com.thing.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "room")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Room extends BaseEntity<Long> {

    private static final long serialVersionUID = 201602010251L;

    @NotNull
    @Column(name = "name", length = 120, nullable = false)
    private String name;

}
