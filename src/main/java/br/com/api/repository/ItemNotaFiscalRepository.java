package br.com.api.repository;

import br.com.api.model.ItemNotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemNotaFiscalRepository extends JpaRepository<ItemNotaFiscal, Long> {

    List<ItemNotaFiscal> findByNotaFiscalId(Long id);
}
