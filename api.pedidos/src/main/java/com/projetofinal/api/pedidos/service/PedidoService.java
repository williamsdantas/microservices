package com.projetofinal.api.pedidos.service;

import com.projetofinal.api.pedidos.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.projetofinal.api.pedidos.model.Pedido;
import com.projetofinal.api.pedidos.dto.PedidoDto;
import java.util.Optional;
import java.util.List;


@AllArgsConstructor
@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoDto criarPedido(PedidoDto pedidoDto){
        Pedido pedido = Pedido.fromDto(pedidoDto);

        return new PedidoDto(pedidoRepository.save(pedido));
    }

    public PedidoDto buscarPorId(Long id) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            return new PedidoDto(pedido);
        }
        return null;
    }

    public PedidoDto atualizarPedido(Long id, PedidoDto pedidoDto) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);

        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();

            pedido.setDataPedido(pedidoDto.dataPedido());
            pedido.setEndereco(pedidoDto.endereco());
            pedido.setIdProduto(pedidoDto.idProduto());
            pedido.setStatus(pedidoDto.status());

            return new PedidoDto(pedidoRepository.save(pedido));
        }
        return null;
    }

    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }


    public List<PedidoDto> buscarTodos() {
        return List.of();
    }
}