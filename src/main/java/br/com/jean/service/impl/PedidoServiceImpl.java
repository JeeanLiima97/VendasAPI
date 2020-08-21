package br.com.jean.service.impl;

import br.com.jean.domain.repository.PedidoRepository;
import br.com.jean.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

}
