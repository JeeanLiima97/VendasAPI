package br.com.jean.domain.repository;

import br.com.jean.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(value= "select c from Cliente c where c.nome like :nome")
    List<Cliente> encontrarPornome(@Param("nome") String nome);

    boolean existsClienteByNome(String nome);
}
