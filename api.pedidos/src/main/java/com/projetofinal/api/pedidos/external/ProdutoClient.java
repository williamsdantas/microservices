package com.projetofinal.api.pedidos.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@FeignClient(name = "produto-service", url = "http://localhost:8081/api/produtos")  // Ajuste a URL conforme necessário
public interface ProdutoClient {

    @PostMapping("/{id}/reservar")
    ResponseEntity<String> reservarProduto(@PathVariable("id") Long id);
}

