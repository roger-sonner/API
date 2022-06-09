package br.com.primeira.primeiraapi.dto;

import br.com.primeira.primeiraapi.model.Cliente;

public class ClienteDto {

    private Long id;
    private Long codigo;
    private String nome;

    public ClienteDto(Cliente cliente){
        this.id = cliente.getId();
        this.codigo = cliente.getCodigo();
        this.nome = cliente.getNome();

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

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }
}
