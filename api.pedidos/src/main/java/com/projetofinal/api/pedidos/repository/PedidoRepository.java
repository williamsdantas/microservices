package com.projetofinal.api.pedidos.repository;

import com.projetofinal.api.pedidos.model.Pedido;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}