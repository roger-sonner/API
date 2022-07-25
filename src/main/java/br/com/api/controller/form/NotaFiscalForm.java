package br.com.api.controller.form;

import br.com.api.repository.ClienteRepository;
import br.com.api.model.Cliente;
import br.com.api.model.NotaFiscal;

import java.time.LocalDate;
import java.util.Optional;

public class NotaFiscalForm {

    private LocalDate data;
    private Long numero;
    private Long clienteId;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public NotaFiscal converterNotaFiscal(ClienteRepository repository) {
        Optional<Cliente> cliente = repository.findById(clienteId);
        return new NotaFiscal(data, numero, cliente.get());
    }
}
