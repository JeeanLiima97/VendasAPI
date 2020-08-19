package br.com.jean.domain.repository;

import br.com.jean.domain.entity.Cliente;
import br.com.jean.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PedidoRepository extends JpaRepository<Pedido,Integer>{

    List<Pedido> findByCliente(Cliente cliente);
}
