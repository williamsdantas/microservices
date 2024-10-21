package com.projetofinal.api.pedidos.controller;

import com.projetofinal.api.pedidos.dto.PedidoDto;
import com.projetofinal.api.pedidos.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping(path = "/pedidos",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)

public class PedidoController {

    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDto> criarPedido(@RequestBody PedidoDto pedidoDto) {
        PedidoDto newPedido = pedidoService.criarPedido(pedidoDto);
        return ResponseEntity.ok(newPedido);
    }
//public ResponseEntity<Page<ProdutoDto>> ProdutosDisponiveis(@PageableDefault(size= 10) Pageable pagination){
    @GetMapping
    public ResponseEntity<Page<PedidoDto>> buscarTodos(@PageableDefault(size= 10) Pageable pagination) {
        return ResponseEntity.ok(pedidoService.buscarTodos(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> buscarPorId(@PathVariable Long id) {
        PedidoDto pedido = pedidoService.buscarPorId(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> atualizarPedido(@PathVariable Long id, @RequestBody PedidoDto pedidoDTO) {
        PedidoDto pedidoAtualizado = pedidoService.atualizarPedido(id, pedidoDTO);
        if (pedidoAtualizado != null) {
            return ResponseEntity.ok(pedidoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
