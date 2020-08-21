package br.com.jean.rest.controller;


import br.com.jean.domain.entity.Pedido;
import br.com.jean.rest.dto.PedidoDTO;
import br.com.jean.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO){
      Pedido pedido =  pedidoService.salvar(pedidoDTO);
        return pedido.getId();
    }

}
