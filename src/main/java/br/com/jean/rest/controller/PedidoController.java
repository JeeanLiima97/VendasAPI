package br.com.jean.rest.controller;


import br.com.jean.domain.entity.ItemPedido;
import br.com.jean.domain.entity.Pedido;
import br.com.jean.domain.entity.enums.StatusPedido;
import br.com.jean.rest.dto.AtualizacaoStatusPedidoDTO;
import br.com.jean.rest.dto.InformacaoItemPedidoDTO;
import br.com.jean.rest.dto.InformacoesPedidoDto;
import br.com.jean.rest.dto.PedidoDTO;
import br.com.jean.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.salvar(pedidoDTO);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDto getById(@PathVariable Integer id) {
        return pedidoService.obterPedidoCompleto(id)
                .map(pedido -> converter(pedido)
                )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id,
                             @RequestBody AtualizacaoStatusPedidoDTO dto) {
        String novoStatus = dto.getNovoStatus();
        pedidoService.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }


    private InformacoesPedidoDto converter(Pedido pedido) {
        return InformacoesPedidoDto.builder().
                codigo(pedido.getId())
                .cpf(pedido.getCliente().getCpf())
                .data(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(converter(pedido.getItens())).build();

    }


    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }
        return itens.stream().map(item -> InformacaoItemPedidoDTO.builder()
                .descricaoProduto(item.getProduto().getDescricao())
                .precoUnitario(item.getProduto().getPreco())
                .quantidade(item.getQuantidade()).build()).collect(Collectors.toList());
    }
}
