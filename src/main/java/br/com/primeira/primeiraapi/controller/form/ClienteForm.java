package br.com.primeira.primeiraapi.controller.form;

import br.com.primeira.primeiraapi.model.Cliente;

public class ClienteForm {

    private Long codigo;
    private String nome;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cliente converterCliente() {
        return new Cliente(codigo, nome);
    }
}
