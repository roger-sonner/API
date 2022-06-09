package br.com.primeira.primeiraapi.repository;

import br.com.primeira.primeiraapi.model.ItemNotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemNotaFiscalRepository extends JpaRepository<ItemNotaFiscal, Long> {

    Optional<ItemNotaFiscal> findByNotaFiscalId(Long id);
}
