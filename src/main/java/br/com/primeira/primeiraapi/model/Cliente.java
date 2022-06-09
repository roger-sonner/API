package br.com.primeira.primeiraapi.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long codigo;
    @NotNull
    private String nome;

    public Cliente(){

    }

    public Cliente(Long codigo, String nome) {  // Esse construtor vai ser usado na classe ClienteController para fazer o post
        this.codigo = codigo;
        this.nome = nome;
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
