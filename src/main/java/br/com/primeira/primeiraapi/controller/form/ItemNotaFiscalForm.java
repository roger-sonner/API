package br.com.primeira.primeiraapi.controller.form;

import br.com.primeira.primeiraapi.model.ItemNotaFiscal;
import br.com.primeira.primeiraapi.model.NotaFiscal;
import br.com.primeira.primeiraapi.model.Produto;
import br.com.primeira.primeiraapi.repository.NotaFiscalRepository;
import br.com.primeira.primeiraapi.repository.ProdutoRepository;

import java.util.Optional;

public class ItemNotaFiscalForm {
    private Double quantidade;
    private Integer sequencial;
    private Long produtoId;
    private Long notaFiscalId;

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

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getNotaFiscalId() {
        return notaFiscalId;
    }

    public void setNotaFiscalId(Long notaFiscalId) {
        this.notaFiscalId = notaFiscalId;
    }

    public ItemNotaFiscal converterItemNotaFiscal(ProdutoRepository produtoRepository, Long produtoId,
                                                  NotaFiscalRepository notaFiscalRepository, Long notaFiscalId) {
        Optional<Produto> optionalProduto = produtoRepository.findById(produtoId);
        Optional<NotaFiscal> optionalNotaFiscal = notaFiscalRepository.findById(notaFiscalId);
        return new ItemNotaFiscal(quantidade, sequencial, optionalNotaFiscal.get(), optionalProduto.get());
    }
}
