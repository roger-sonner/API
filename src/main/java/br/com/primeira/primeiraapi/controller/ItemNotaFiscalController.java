package br.com.primeira.primeiraapi.controller;

import br.com.primeira.primeiraapi.model.ItemNotaFiscal;
import br.com.primeira.primeiraapi.model.NotaFiscal;
import br.com.primeira.primeiraapi.repository.ItemNotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/itensNotaFiscal")
public class ItemNotaFiscalController {

    @Autowired
    private ItemNotaFiscalRepository itemNotaFiscalRepository;

    @GetMapping
    public List<ItemNotaFiscal> getItensNotasFiscais() {
        List<ItemNotaFiscal> itemNotaFiscal = itemNotaFiscalRepository.findAll();

        return itemNotaFiscal;
    }

    @GetMapping("/{id}")  // @PathVariable indica que o parametro vem na url
    public ResponseEntity<ItemNotaFiscal> getItemNotaFiscal(@PathVariable Long id) {  // o parametro no GetMapping deve ter o mesmo do método (ex: sgetCliente(Long id))
        Optional<ItemNotaFiscal> optional = itemNotaFiscalRepository.findByNotaFiscalId(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optional.get());
    }
}
