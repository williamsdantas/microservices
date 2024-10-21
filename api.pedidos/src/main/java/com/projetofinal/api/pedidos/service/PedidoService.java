package com.projetofinal.api.pedidos.service;

import com.projetofinal.api.pedidos.external.ProdutoClient;
import com.projetofinal.api.pedidos.model.StatusPedido;
import com.projetofinal.api.pedidos.repository.PedidoRepository;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import com.projetofinal.api.pedidos.model.Pedido;
import com.projetofinal.api.pedidos.dto.PedidoDto;
import java.util.Optional;
import java.util.List;


@AllArgsConstructor
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoClient produtoClient;

    public PedidoDto criarPedido(PedidoDto pedidoDto) {
        Pedido pedido = Pedido.fromDto(pedidoDto);
        try{
            // Reservar o produto via Feign Client
            ResponseEntity<String> response = produtoClient.reservarProduto(pedido.getIdProduto());

            if (response.getStatusCode().is2xxSuccessful()) {
                // Quantidade reservada com sucesso, então confirma o pedido
                pedido.setStatus(StatusPedido.CONFIRMADO);
            }else if (response.getStatusCode().is4xxClientError()) {
                pedido.setStatus(StatusPedido.CANCELADO);
            }

        }
        catch (FeignException e) {
            // Caso ocorra um erro na comunicação com o microservice de produtos
            pedido.setStatus(StatusPedido.CANCELADO);
            
        }

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