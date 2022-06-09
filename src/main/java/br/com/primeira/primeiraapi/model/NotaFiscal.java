package br.com.primeira.primeiraapi.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class NotaFiscal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numero;
    @ManyToOne
    private Cliente cliente;
    @OneToMany(mappedBy = "notaFiscal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ItemNotaFiscal> itemNotaFiscal;
    private LocalDate data;

    public NotaFiscal(){
    }

    public NotaFiscal(LocalDate data, Long numero, Cliente cliente){
        this.data = data;
        this.numero = numero;
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
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
