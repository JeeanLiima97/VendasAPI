package br.com.jean.service.impl;

import br.com.jean.domain.entity.Cliente;
import br.com.jean.domain.entity.ItemPedido;
import br.com.jean.domain.entity.Pedido;
import br.com.jean.domain.entity.Produto;
import br.com.jean.domain.repository.ClienteRepository;
import br.com.jean.domain.repository.ItemPedidoRepository;
import br.com.jean.domain.repository.PedidoRepository;
import br.com.jean.domain.repository.ProdutoRepository;
import br.com.jean.exception.RegraNegocioException;
import br.com.jean.rest.dto.ItemPedidoDTO;
import br.com.jean.rest.dto.PedidoDTO;
import br.com.jean.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {
        Integer idCliente = pedidoDTO.getCliente();
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));


        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemPedidos = converterItens(pedido, pedidoDTO.getItens());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itemPedidos);
        pedido.setItens(itemPedidos);
        return pedido;


    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens) {
        if (itens.isEmpty()) {
            throw new RegraNegocioException("Não é possivel realizar o pedido sem itens");
        }
        return itens
                .stream()
                .map(itemPedidoDTO -> {
                    Integer idProduto = itemPedidoDTO.getProduto();
                    Produto produto = produtoRepository.findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException(
                                    "Código de produto inválido: " + idProduto
                            ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
