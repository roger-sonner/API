package br.com.primeira.primeiraapi.controller.form;

import br.com.primeira.primeiraapi.model.Cliente;
import br.com.primeira.primeiraapi.model.NotaFiscal;
import br.com.primeira.primeiraapi.model.Produto;
import br.com.primeira.primeiraapi.repository.ClienteRepository;
import br.com.primeira.primeiraapi.repository.NotaFiscalRepository;
import br.com.primeira.primeiraapi.repository.ProdutoRepository;

import java.time.LocalDate;
import java.util.Optional;

public class putNotaFiscalForm {

    private LocalDate data;
    private Long clienteId;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public NotaFiscal atualizar(Long id, NotaFiscalRepository notaFiscalRepository, ClienteRepository clienteRepository, Long clienteId) {
        NotaFiscal notaFiscal = notaFiscalRepository.getReferenceById(id);
        Cliente cliente = clienteRepository.getReferenceById(clienteId);
        notaFiscal.setData(this.data);
        notaFiscal.setCliente(cliente);
        return notaFiscal;
    }
}
