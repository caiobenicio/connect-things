package br.com.thing.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_dispositivo")
@AttributeOverride(name = "id", column = @Column(name = "pk_id"))
public class DispositivoEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 201602010251L;


    @NotNull
    @Size(min = 2, max = 45)
    @Column(name = "funcao", length = 45, nullable = false)
    private String funcao;

    @NotNull
    @Column(name = "estado", length = 255, nullable = false)
    private String estado;


    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public DispositivoEntity(String funcao, String estado) {
        this.funcao = funcao;
        this.estado = estado;
    }

    public DispositivoEntity() {
    }


}
