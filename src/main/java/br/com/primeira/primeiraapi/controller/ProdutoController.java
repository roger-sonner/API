package br.com.primeira.primeiraapi.controller;

import br.com.primeira.primeiraapi.controller.form.ProdutoForm;
import br.com.primeira.primeiraapi.controller.form.putClienteForm;
import br.com.primeira.primeiraapi.controller.form.putProdutoForm;
import br.com.primeira.primeiraapi.dto.ClienteDto;
import br.com.primeira.primeiraapi.dto.ProdutoDto;
import br.com.primeira.primeiraapi.model.Cliente;
import br.com.primeira.primeiraapi.model.Produto;
import br.com.primeira.primeiraapi.repository.ProdutoRepository;
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
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> getProdutos(String nomeProduto) {  // recebe o parametro (nomeProduto) da url
        if(nomeProduto == null) {
            List<Produto> produtos = produtoRepository.findAll();
            return produtos;
        } else{
            List<Produto> produtos = produtoRepository.findByDescricao(nomeProduto);
            return produtos;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> getProduto(@PathVariable Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()) {
            return ResponseEntity.ok(new ProdutoDto(produto.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> postProduto(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder){
        Produto produto = form.converterProduto();
        produtoRepository.save(produto);

        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDto(produto));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoDto> putProduto(@PathVariable Long id, @RequestBody @Valid putProdutoForm form){
        Optional<Produto> optional = produtoRepository.findById(id);
        if(optional.isPresent()) {
            Produto produto = form.atualizar(id, produtoRepository);
            return ResponseEntity.ok(new ProdutoDto(produto));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteProduto(@PathVariable Long id){
        Optional<Produto> optional = produtoRepository.findById(id);
        if(optional.isPresent()) {
            produtoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
