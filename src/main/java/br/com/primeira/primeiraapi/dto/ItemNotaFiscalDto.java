package br.com.primeira.primeiraapi.dto;

import br.com.primeira.primeiraapi.model.NotaFiscal;
import br.com.primeira.primeiraapi.model.Produto;

import java.math.BigDecimal;

public class ItemNotaFiscalDto {
    private Long id;
    private Double quantidade;
    private Integer sequencial;
    private BigDecimal valorTotal;
    private Long notaFiscal;
    private Long produto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getSequencial() {
        return sequencial;
    }

    public void setSequencial(Integer sequencial) {
        this.sequencial = sequencial;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getNotaFiscal() {
        return notaFiscal;
    }

    public Long getProduto() {
        return produto;
    }

    public void setNotaFiscal(Long notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public void setProduto(Long produto) {
        this.produto = produto;
    }


}
