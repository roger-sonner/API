package br.com.primeira.primeiraapi.repository;

import br.com.primeira.primeiraapi.model.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {
}

