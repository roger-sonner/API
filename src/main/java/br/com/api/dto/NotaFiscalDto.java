package br.com.api.dto;

import br.com.api.model.ItemNotaFiscal;
import br.com.api.model.Cliente;
import br.com.api.model.NotaFiscal;

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

    public NotaFiscalDto(NotaFiscal notaFiscal) {
    }
    public NotaFiscalDto(NotaFiscal notaFiscal, Cliente cliente){
        this.id = notaFiscal.getId();
        this.numero = notaFiscal.getNumero();
        this.cliente = cliente.getId();
        this.data = notaFiscal.getData();

    }

    public NotaFiscalDto(List<NotaFiscal> notaFiscal) {

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
