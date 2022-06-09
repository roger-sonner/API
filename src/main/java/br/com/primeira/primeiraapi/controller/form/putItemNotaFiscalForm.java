package br.com.primeira.primeiraapi.controller.form;

import br.com.primeira.primeiraapi.model.Cliente;
import br.com.primeira.primeiraapi.model.ItemNotaFiscal;
import br.com.primeira.primeiraapi.model.NotaFiscal;
import br.com.primeira.primeiraapi.model.Produto;
import br.com.primeira.primeiraapi.repository.ClienteRepository;
import br.com.primeira.primeiraapi.repository.ItemNotaFiscalRepository;
import br.com.primeira.primeiraapi.repository.NotaFiscalRepository;
import br.com.primeira.primeiraapi.repository.ProdutoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public class putItemNotaFiscalForm {

    private Long itemNotaFiscalId;
    private Double quantidade;
    private Double novaQuantidade;
    private Integer sequencial;
    private Integer novoSequencial;
    private Long produtoId;
    private Long novoProdutoId;

    public Long getItemNotaFiscalId() {
        return itemNotaFiscalId;
    }

    public void setItemNotaFiscalId(Long itemNotaFiscalId) {
        this.itemNotaFiscalId = itemNotaFiscalId;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getNovaQuantidade() {
        return novaQuantidade;
    }

    public void setNovaQuantidade(Double novaQuantidade) {
        this.novaQuantidade = novaQuantidade;
    }

    public Integer getSequencial() {
        return sequencial;
    }

    public void setSequencial(Integer sequencial) {
        this.sequencial = sequencial;
    }

    public Integer getNovoSequencial() {
        return novoSequencial;
    }

    public void setNovoSequencial(Integer novoSequencial) {
        this.novoSequencial = novoSequencial;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getNovoProdutoId() {
        return novoProdutoId;
    }

    public void setNovoProdutoId(Long novoProdutoId) {
        this.novoProdutoId = novoProdutoId;
    }

    public ItemNotaFiscal atualizar(Long itemNotaFiscalId,
                                    ItemNotaFiscalRepository itemNotaFiscalRepository,
                                    ProdutoRepository produtoRepository,
                                    Long produtoId,
                                    BigDecimal valorTotal,
                                    Integer sequencial,
                                    Double quantidade) {

        ItemNotaFiscal itemNotaFiscal = itemNotaFiscalRepository.getReferenceById(itemNotaFiscalId);
        Produto produto = produtoRepository.getReferenceById(produtoId);
        itemNotaFiscal.setQuantidade(quantidade);
        itemNotaFiscal.setSequencial(sequencial);
        itemNotaFiscal.setProduto(produto);
        itemNotaFiscal.setValorTotal(valorTotal);

        return itemNotaFiscal;
    }
}
