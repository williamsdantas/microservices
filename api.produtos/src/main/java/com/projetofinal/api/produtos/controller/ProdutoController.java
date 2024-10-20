package com.projetofinal.api.produtos.controller;

import com.projetofinal.api.produtos.dto.ProdutoDto;
import com.projetofinal.api.produtos.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/produtos",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> registrar(@RequestBody @Valid ProdutoDto ProdutoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.cadastrar(ProdutoDto));
    }
}
