package br.com.jean.domain.repository;


import br.com.jean.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClienteReposiory extends JpaRepository<Cliente, Integer> {


//    List<Cliente>  findByNameLike(String nome);
}
