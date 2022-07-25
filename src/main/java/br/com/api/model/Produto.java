package br.com.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long codigo;


    private String descricao;
    private BigDecimal valorUnitario;

    public Produto() {
    }

    public Produto(Long codigo, String descricao, BigDecimal valorUnitario){
        this.codigo = codigo;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
    }


    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getCodigo() {

        return codigo;
    }

    public void setCodigo(Long codigo) {

        this.codigo = codigo;
    }

    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    public BigDecimal getValorUnitario() {

        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {

        this.valorUnitario = valorUnitario;
    }
}
