package br.com.thing.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_comodo")
@AttributeOverride(name = "id", column = @Column(name = "pk_id"))
public class ComodoEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 201602010251L;


    @NotNull
    @Column(name = "nome", length = 120, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "modulos_presentes", length = 120, nullable = false)
    private String modulos_presentes;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModulos_presentes() {
        return modulos_presentes;
    }

    public void setModulos_presentes(String modulos_presentes) {
        this.modulos_presentes = modulos_presentes;
    }

    public ComodoEntity() {
    }

    public ComodoEntity(String nome, String modulos_presentes) {
        this.nome = nome;
        this.modulos_presentes = modulos_presentes;
    }
}
