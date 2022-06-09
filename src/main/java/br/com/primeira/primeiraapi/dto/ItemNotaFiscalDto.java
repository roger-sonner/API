package br.com.primeira.primeiraapi.dto;

import br.com.primeira.primeiraapi.model.ItemNotaFiscal;
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

    public ItemNotaFiscalDto() {
    }

    public ItemNotaFiscalDto(ItemNotaFiscal itemNotaFiscal) {
    }

    public ItemNotaFiscalDto(NotaFiscal notaFiscal, Produto produto) {
        this.id = getId();
        this.quantidade = getQuantidade();
        this.sequencial = getSequencial();
        this.valorTotal = getValorTotal();
        this.notaFiscal = notaFiscal.getId();
        this.produto = produto.getId();
    }

    public ItemNotaFiscalDto(ItemNotaFiscal itemNotaFiscal, Produto produto, NotaFiscal notaFiscal) {
        this.id = getId();
        this.quantidade = itemNotaFiscal.getQuantidade();
        this.sequencial = itemNotaFiscal.getSequencial();
        this.valorTotal = itemNotaFiscal.getValorTotal();
        this.notaFiscal = notaFiscal.getId();
        this.produto = produto.getId();
    }

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
