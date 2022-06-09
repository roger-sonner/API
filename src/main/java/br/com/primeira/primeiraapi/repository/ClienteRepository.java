package br.com.primeira.primeiraapi.repository;

import br.com.primeira.primeiraapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNome(String nomeCliente);


}
