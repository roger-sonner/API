package br.com.primeira.primeiraapi.repository;

import br.com.primeira.primeiraapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByDescricao(String nomeProduto);
}
