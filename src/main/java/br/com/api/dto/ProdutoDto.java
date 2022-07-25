package br.com.api.dto;

import br.com.api.model.Produto;

import java.math.BigDecimal;

public class ProdutoDto {

    private Long id;
    private Long codigo;
    private String descricao;
    private BigDecimal valorUnitario;

    public ProdutoDto(Produto produto){
        this.id = produto.getId();
        this.codigo = produto.getCodigo();
        this.descricao = produto.getDescricao();
        this.valorUnitario = produto.getValorUnitario();

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
