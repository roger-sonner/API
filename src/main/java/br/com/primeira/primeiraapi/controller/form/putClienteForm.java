package br.com.primeira.primeiraapi.controller.form;

import br.com.primeira.primeiraapi.model.Cliente;
import br.com.primeira.primeiraapi.repository.ClienteRepository;

public class putClienteForm {

    private String nome;

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public Cliente atualizar(Long id, ClienteRepository clienteRepository) {
        Cliente cliente = clienteRepository.getReferenceById(id);
        cliente.setNome(this.nome);
        return cliente;
    }
}

