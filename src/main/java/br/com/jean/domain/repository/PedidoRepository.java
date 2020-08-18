package br.com.jean.domain.repository;

import br.com.jean.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Integer>{
}
