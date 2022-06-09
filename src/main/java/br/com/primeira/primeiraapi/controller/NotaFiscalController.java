package br.com.primeira.primeiraapi.controller;

import br.com.primeira.primeiraapi.controller.form.NotaFiscalForm;
import br.com.primeira.primeiraapi.controller.form.putNotaFiscalForm;
import br.com.primeira.primeiraapi.dto.NotaFiscalDto;
import br.com.primeira.primeiraapi.model.Cliente;
import br.com.primeira.primeiraapi.model.NotaFiscal;
import br.com.primeira.primeiraapi.repository.ClienteRepository;
import br.com.primeira.primeiraapi.repository.NotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/notasFiscais")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<NotaFiscal> getNotasFiscais() {
        List<NotaFiscal> notasFiscais = notaFiscalRepository.findAll();

        return notasFiscais;
    }

    @GetMapping("/{id}")  // @PathVariable indica que o parametro vem na url
    public ResponseEntity<NotaFiscal> getNotaFiscal(@PathVariable Long id) {  // o parametro no GetMapping deve ter o mesmo do método (ex: sgetCliente(Long id))
        Optional<NotaFiscal> optional = notaFiscalRepository.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optional.get());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<NotaFiscalDto> postNotaFiscal(@RequestBody @Valid NotaFiscalForm form, UriComponentsBuilder uriBuilder) {
        Optional<Cliente> optional = clienteRepository.findById(form.getClienteId());
        if (optional.isPresent()) {
            NotaFiscal notaFiscal = form.converterNotaFiscal(clienteRepository);
            notaFiscalRepository.save(notaFiscal);

            URI uri = uriBuilder.path("/notasFiscais/{id}").buildAndExpand(notaFiscal.getId()).toUri();
            return ResponseEntity.created(uri).body(new NotaFiscalDto(notaFiscal, notaFiscal.getCliente()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<NotaFiscalDto> putNotaFiscal(@PathVariable Long id, @RequestBody @Valid putNotaFiscalForm form) {
        Optional<NotaFiscal> optionalNotaFiscal = notaFiscalRepository.findById(id);
        Optional<Cliente> optionalCliente = clienteRepository.findById(form.getClienteId());
        if (!optionalNotaFiscal.isPresent() && !optionalCliente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        NotaFiscal notaFiscal = form.atualizar(id, notaFiscalRepository, clienteRepository, form.getClienteId());
        return ResponseEntity.ok(new NotaFiscalDto(notaFiscal));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteNotaFiscal(@PathVariable Long id) {
        Optional<NotaFiscal> optionalNotaFiscal = notaFiscalRepository.findById(id);
        if (!optionalNotaFiscal.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        notaFiscalRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
