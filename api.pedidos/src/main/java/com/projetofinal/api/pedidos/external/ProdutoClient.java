package com.projetofinal.api.pedidos.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

@FeignClient(name = "api.produtos" )
public interface ProdutoClient {

    @PostMapping(value = "/produtos/{id}/reservar" , consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> reservarProduto(@PathVariable("id") Long id);
}

