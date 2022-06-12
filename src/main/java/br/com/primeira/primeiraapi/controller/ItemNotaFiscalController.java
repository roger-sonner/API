package br.com.primeira.primeiraapi.controller;

import br.com.primeira.primeiraapi.controller.form.ItemNotaFiscalForm;
import br.com.primeira.primeiraapi.controller.form.putItemNotaFiscalForm;
import br.com.primeira.primeiraapi.dto.ItemNotaFiscalDto;
import br.com.primeira.primeiraapi.model.ItemNotaFiscal;
import br.com.primeira.primeiraapi.model.NotaFiscal;
import br.com.primeira.primeiraapi.model.Produto;
import br.com.primeira.primeiraapi.repository.ItemNotaFiscalRepository;
import br.com.primeira.primeiraapi.repository.NotaFiscalRepository;
import br.com.primeira.primeiraapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/itensNotaFiscal")
public class ItemNotaFiscalController {

    @Autowired
    private ItemNotaFiscalRepository itemNotaFiscalRepository;

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ItemNotaFiscalDto> postItemNotaFiscal(@RequestBody @Valid ItemNotaFiscalForm form, UriComponentsBuilder uriBuilder) {
        Optional<Produto> optionalProduto = produtoRepository.findById(form.getProdutoId());
        Optional<NotaFiscal> optionalNotaFiscal = notaFiscalRepository.findById(form.getNotaFiscalId());

        Double quantidade = form.getQuantidade();
        if((quantidade == null) || (quantidade <= 0.0)){
            quantidade = 1.0;
        }

        if (optionalProduto.isPresent() && optionalNotaFiscal.isPresent()) {
            ItemNotaFiscal itemNotaFiscal = form.converterItemNotaFiscal(
                    produtoRepository, form.getProdutoId(),
                    notaFiscalRepository, form.getNotaFiscalId());

            // Calcula o valor total com base na quantidade e valor unitário do produto
            itemNotaFiscal.setValorTotal(CalculaValorTotal(optionalProduto.get().getValorUnitario(), quantidade));
            itemNotaFiscal.setQuantidade(quantidade);
            itemNotaFiscalRepository.save(itemNotaFiscal);

            URI uri = uriBuilder.path("/itensNotaFiscal/{id}").buildAndExpand(itemNotaFiscal.getId()).toUri();
            return ResponseEntity
                    .created(uri)
                    .body(new ItemNotaFiscalDto(itemNotaFiscal,
                            itemNotaFiscal.getProduto(),
                            itemNotaFiscal.getNotaFiscal()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ItemNotaFiscalDto> putItemNotafiscalNovo(@RequestBody @Valid putItemNotaFiscalForm form){

        Long itemNotaFiscalId = form.getItemNotaFiscalId();
        Long produtoId = form.getProdutoId();
        Integer sequencial = form.getSequencial();
        Double quantidade = form.getQuantidade();

        Optional<ItemNotaFiscal> optionalItemNotaFiscal = itemNotaFiscalRepository.findById(itemNotaFiscalId);
        if(!optionalItemNotaFiscal.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Optional<Produto> optionalProduto = produtoRepository.findById(produtoId);
        if (!optionalProduto.isPresent()) {
            produtoId = optionalItemNotaFiscal.get().getProduto().getId();
            optionalProduto = produtoRepository.findById(produtoId);
        }

        BigDecimal valorUnitario = optionalProduto.get().getValorUnitario();

        if((sequencial == null) || (sequencial <= 0)){
            sequencial = optionalItemNotaFiscal.get().getSequencial();
        }
        if((quantidade == null) || (quantidade <= 0)){
            quantidade = optionalItemNotaFiscal.get().getQuantidade();
        }

        ItemNotaFiscal itemNotaFiscal = form.atualizar(itemNotaFiscalId,
                itemNotaFiscalRepository,
                produtoRepository,
                produtoId,
                CalculaValorTotal(valorUnitario, quantidade),
                sequencial,
                quantidade);
        return ResponseEntity.ok(new ItemNotaFiscalDto(itemNotaFiscal));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteItemNotaFiscal(@PathVariable Long id) {
        Optional<ItemNotaFiscal> optionalItemNotaFiscal = itemNotaFiscalRepository.findById(id);
        if (!optionalItemNotaFiscal.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        itemNotaFiscalRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private BigDecimal CalculaValorTotal(BigDecimal valorUnitario, Double quantidade) {

        return valorUnitario.multiply(new BigDecimal(quantidade));

    }
}
