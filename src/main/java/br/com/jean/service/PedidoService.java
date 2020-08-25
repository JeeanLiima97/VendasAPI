package br.com.jean.service;


import br.com.jean.domain.entity.Pedido;
import br.com.jean.domain.entity.enums.StatusPedido;
import br.com.jean.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO pedidoDTO);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
