package com.projetofinal.api.pedidos.model;

import com.projetofinal.api.pedidos.dto.PedidoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataPedido;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private Long idProduto;  // Mantém apenas o ID do produto, sem relacionamento

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;

    public static Pedido fromDto(PedidoDto pedidoDto) {
        return new Pedido(null, pedidoDto.dataPedido(),  pedidoDto.endereco(),pedidoDto.idProduto(),pedidoDto.status());
    }
}
