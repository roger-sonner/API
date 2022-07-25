package br.com.api.controller.form;

import br.com.api.repository.ClienteRepository;
import br.com.api.model.Cliente;

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

