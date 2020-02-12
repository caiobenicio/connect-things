package br.com.thing.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_residencia")
@AttributeOverride(name = "id", column = @Column(name = "pk_id"))
public class ResidenciaEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 201602010251L;


    @NotNull
    @Column(name = "qtdmorador", length = 120, nullable = false)
    private int qtdmorador;



    public ResidenciaEntity() {
    }

    public ResidenciaEntity(int qtdmorador) {
        this.qtdmorador = qtdmorador;
    }

    public int getQtdmorador() {
        return qtdmorador;
    }

    public void setQtdmorador(int qtdmorador) {
        this.qtdmorador = qtdmorador;
    }
}
