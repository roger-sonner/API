package br.com.primeira.primeiraapi.dto;

import br.com.primeira.primeiraapi.model.Cliente;
import br.com.primeira.primeiraapi.model.ItemNotaFiscal;
import br.com.primeira.primeiraapi.model.NotaFiscal;

import java.time.LocalDate;
import java.util.List;

public class NotaFiscalDto {

    private Long id;
    private Long numero;
    private Long cliente;
    private List<ItemNotaFiscal> itemNotaFiscal;
    private LocalDate data;

    public NotaFiscalDto(){

    }

    public NotaFiscalDto(NotaFiscal notaFiscal, Cliente cliente){
        this.id = notaFiscal.getId();
        this.numero = notaFiscal.getNumero();
        this.cliente = cliente.getId();
        this.itemNotaFiscal = notaFiscal.getItemNotaFiscal();
        this.data = notaFiscal.getData();

    }

    public NotaFiscalDto(List<NotaFiscal> notaFiscal) {

    }

    public NotaFiscalDto(NotaFiscal notaFiscal) {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public List<ItemNotaFiscal> getItemNotaFiscal() {
        return itemNotaFiscal;
    }

    public void setItemNotaFiscal(List<ItemNotaFiscal> itemNotaFiscal) {
        this.itemNotaFiscal = itemNotaFiscal;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
