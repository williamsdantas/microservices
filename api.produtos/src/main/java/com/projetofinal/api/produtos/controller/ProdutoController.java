package com.projetofinal.api.produtos.controller;

import com.projetofinal.api.produtos.dto.ProdutoDto;
import com.projetofinal.api.produtos.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> ProdutosDisponiveis(@PageableDefault(size= 10) Pageable pagination){
        return ResponseEntity.ok(produtoService.findAll(pagination));
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoDto ProdutoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.cadastrar(ProdutoDto));
    }

    @PostMapping("/{id}/reservar")
    public ResponseEntity<String> reservarProduto(@PathVariable Long id, @RequestParam Integer qt) {
        produtoService.reservarProduto(id, qt);
        return ResponseEntity.ok("Quantidade reservada com sucesso.");
    }
}
