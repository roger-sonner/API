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

    private boolean testeErro = false;
    @Autowired
    private ItemNotaFiscalRepository itemNotaFiscalRepository;

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

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

    @PostMapping
    @Transactional
    public ResponseEntity<ItemNotaFiscalDto> postItemNotaFiscal(@RequestBody @Valid ItemNotaFiscalForm form, UriComponentsBuilder uriBuilder) {
        Optional<Produto> optionalProduto = produtoRepository.findById(form.getProdutoId());
        Optional<NotaFiscal> optionalNotaFiscal = notaFiscalRepository.findById(form.getNotaFiscalId());

        if (optionalProduto.isPresent() && optionalNotaFiscal.isPresent()) {
            ItemNotaFiscal itemNotaFiscal = form.converterItemNotaFiscal(
                    produtoRepository, form.getProdutoId(),
                    notaFiscalRepository, form.getNotaFiscalId());


            // Calcula o valor total com base na quantidade e valor unitário do produto
            itemNotaFiscal.setValorTotal(CalculaValorTotal(optionalProduto.get().getValorUnitario(), form.getQuantidade()));

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
        Long oldProdutoId = null;
        Integer sequencial = form.getSequencial();
        Integer oldSequencial = null;
        Double quantidade = form.getQuantidade();
        Double oldQuantidade = null;

        System.out.println("==> putItemNotafiscalNovo <==");
        System.out.println("itemNotaFiscalId -> " + form.getItemNotaFiscalId());
        System.out.println("produtoId -> " + form.getProdutoId());
        System.out.println("novoProdutoId -> " + form.getNovoProdutoId());
        System.out.println("sequencial -> " + form.getSequencial());
        System.out.println("novoSequencial -> " + form.getNovoSequencial());
        System.out.println("quantidade -> " + form.getQuantidade());
        System.out.println("novaQuantidade -> " + form.getNovaQuantidade());

        Optional<ItemNotaFiscal> optionalItemNotaFiscal = itemNotaFiscalRepository.findById(itemNotaFiscalId);
        if(!optionalItemNotaFiscal.isPresent()){
            System.out.println("NÃO ENCONTREI O ITEM DA NOTA FICAL NA TABELA");
            testeErro = true;
            return ResponseEntity.notFound().build();
        }

        Optional<Produto> optionalProduto = produtoRepository.findById(produtoId);
        if (!optionalProduto.isPresent()) {
            System.out.println("ENTREI NA CONDIÇÃO DE PRODUTO NÃO ENCONTRADO OU NULO");
            testeErro = true;
            produtoId = optionalItemNotaFiscal.get().getProduto().getId();
        }

        if((sequencial == null) || (sequencial <= 0)){
            System.out.println("SEQUENCIAL NULO OU MENOR OU IGUAL A ZERO");
            testeErro = true;
            sequencial = optionalItemNotaFiscal.get().getSequencial();
        }
        if((quantidade == null) || (quantidade <= 0)){
            System.out.println("QUANTIDADE NULA OU MENOR OU IGUAL A ZERO");
            testeErro = true;
            quantidade = optionalItemNotaFiscal.get().getQuantidade();
        }

        if(testeErro){
            System.out.println("**********************************");
            System.out.println("**********************************");
            System.out.println("=> TEM COISA ERRADA LÁ EM CIMA <=");
            System.out.println("**********************************");
            System.out.println("**********************************");
        }


        return ResponseEntity.notFound().build();

//        ItemNotaFiscal itemNotaFiscal = form.atualizar(itemNotaFiscalId,
//                itemNotaFiscalRepository,
//                produtoRepository,
//                produtoId,
//                CalculaValorTotal(optionalProduto.get().getValorUnitario(), quantidade),
//                sequencial,
//                quantidade);
//        return ResponseEntity.ok(new ItemNotaFiscalDto(itemNotaFiscal));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ItemNotaFiscalDto> putItemNotaFiscal(@PathVariable
                                                               Long itemNotaFiscalId,
                                                               Long produtoId,
                                                               Integer sequencial,
                                                               Double quantidade,
                                                               @RequestBody @Valid putItemNotaFiscalForm form) {

        System.out.println("itemNotaFiscalId -> " + itemNotaFiscalId);
        System.out.println("produtoId -> " + produtoId);
        System.out.println("sequencial -> " + sequencial);
        System.out.println("quantidade -> " + quantidade);

        return ResponseEntity.notFound().build();
//
//        Optional<ItemNotaFiscal> optionalItemNotaFiscal = itemNotaFiscalRepository.findById(itemNotaFiscalId);
//        Optional<Produto> optionalProduto = produtoRepository.findById(produtoId);
//        if ((!optionalItemNotaFiscal.isPresent() && !optionalProduto.isPresent())) {
//            return ResponseEntity.notFound().build();
//        }
//        if((sequencial == null) || (sequencial <= 0)) {
//            sequencial = optionalItemNotaFiscal.get().getSequencial();
//        }
//        if((quantidade == null) || (quantidade <= 0 ) ){
//            quantidade = optionalItemNotaFiscal.get().getQuantidade();
//        }
//        ItemNotaFiscal itemNotaFiscal = form.atualizar(itemNotaFiscalId,
//                itemNotaFiscalRepository,
//                produtoRepository,
//                produtoId,
//                CalculaValorTotal(optionalProduto.get().getValorUnitario(), quantidade),
//                sequencial,
//                quantidade);
//        return ResponseEntity.ok(new ItemNotaFiscalDto(itemNotaFiscal));
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
