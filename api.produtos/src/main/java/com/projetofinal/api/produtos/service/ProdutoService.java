package com.projetofinal.api.produtos.service;


import com.projetofinal.api.produtos.dto.ProdutoDto;
import com.projetofinal.api.produtos.exception.EstoqueInsuficienteException;
import com.projetofinal.api.produtos.model.Produto;
import com.projetofinal.api.produtos.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoDto findById(Long id) {
        Produto produto = produtoRepository.getReferenceById(id);
        return new ProdutoDto(produto);
    }

    public Page<ProdutoDto> findAll(Pageable pagination){
        return produtoRepository.findAll(pagination).map(p -> new ProdutoDto(p));
    }

    @Transactional
    public ProdutoDto cadastrar(ProdutoDto produtoDto) {
        Produto produto = Produto.fromDto(produtoDto);
        return new ProdutoDto(produtoRepository.save(produto));

    }

    @Transactional
    public boolean reservarProduto(Long id, Integer quantidadeSolicitada) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);

        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            if (produto.getQuantidade() >= quantidadeSolicitada) {
                // Reduzir a quantidade disponível
                produto.setQuantidade(produto.getQuantidade() - quantidadeSolicitada);
                produtoRepository.save(produto); // Salvar a nova quantidade no banco de dados
                return true;
            } else {
                throw new EstoqueInsuficienteException("Quantidade insuficiente para o produto com id: " + id);
            }
        } else {
            throw new EstoqueInsuficienteException("Produto não encontrado com id: " + id);
        }
    }
}
