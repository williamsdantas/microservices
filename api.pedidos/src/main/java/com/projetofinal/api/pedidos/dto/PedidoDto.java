package com.projetofinal.api.pedidos.dto;

import com.projetofinal.api.pedidos.model.Pedido;
import com.projetofinal.api.pedidos.model.StatusPedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PedidoDto(
        Long id,
        @NotNull LocalDate dataPedido,
        @NotNull Long idProduto,
        @NotBlank String endereco,
        @NotNull StatusPedido status
){

    public PedidoDto(Pedido pedido) {
        this(pedido.getId(),pedido.getDataPedido(),pedido.getIdProduto(), pedido.getEndereco(),pedido.getStatus());
    }


}