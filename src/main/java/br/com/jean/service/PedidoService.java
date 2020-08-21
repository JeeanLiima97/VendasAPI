package br.com.jean.service;


import br.com.jean.domain.entity.Pedido;
import br.com.jean.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO pedidoDTO);

}
