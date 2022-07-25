package br.com.api.controller.form;

import br.com.api.repository.ClienteRepository;
import br.com.api.repository.NotaFiscalRepository;
import br.com.api.model.Cliente;
import br.com.api.model.NotaFiscal;

import java.time.LocalDate;

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
