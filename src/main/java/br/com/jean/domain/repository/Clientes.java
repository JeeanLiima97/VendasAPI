package br.com.jean.domain.repository;

import br.com.jean.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Clientes extends JpaRepository<Cliente, Integer> {

    boolean existsClienteByNome(String nome);
}
