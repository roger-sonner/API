package br.com.api.controller;

import br.com.api.controller.form.NotaFiscalForm;
import br.com.api.controller.form.putNotaFiscalForm;
import br.com.api.dto.NotaFiscalDto;
import br.com.api.repository.ClienteRepository;
import br.com.api.repository.NotaFiscalRepository;
import br.com.api.model.Cliente;
import br.com.api.model.NotaFiscal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 10800)  // 10800 = 3 horas
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
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        NotaFiscal notaFiscal = form.converterNotaFiscal(clienteRepository);
        notaFiscalRepository.save(notaFiscal);

        URI uri = uriBuilder.path("/notasFiscais/{id}").buildAndExpand(notaFiscal.getId()).toUri();
        return ResponseEntity.created(uri).body(new NotaFiscalDto(notaFiscal, notaFiscal.getCliente()));

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
