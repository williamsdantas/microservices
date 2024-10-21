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
    public void reservarProduto(Long id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);

        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            if (produto.getQuantidade() >= 1) {
                // Reduzir a quantidade disponível
                produto.setQuantidade(produto.getQuantidade() - 1);
                produtoRepository.save(produto); // Salvar a nova quantidade no banco de dados
            } else {
                throw new EstoqueInsuficienteException("Quantidade insuficiente para o produto com id: " + id);
            }
        } else {
            throw new EstoqueInsuficienteException("Produto não encontrado com id: " + id);
        }
    }

    @Transactional
    public ProdutoDto atualizarProduto(Long id, ProdutoDto produtoDto) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);

        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get(); // Recupera o produto existente

            // Atualiza os campos do produto com os dados do DTO
            produto.setNome(produtoDto.nome());
            produto.setDescricao(produtoDto.descricao());
            produto.setQuantidade(produtoDto.quantidade());
            produto.setPreco(produtoDto.preco());

            // Salva o produto atualizado no banco de dados e retorna o DTO
            return new ProdutoDto(produtoRepository.save(produto));
        }
        throw new EstoqueInsuficienteException("Produto não encontrado com id: " + id);
    }


    @Transactional
    public void delete(Long id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);

        if (produtoOpt.isPresent()) {
            produtoRepository.deleteById(id);
        }
        else {
            throw new EstoqueInsuficienteException("Produto não encontrado com id: " + id);
        }
    }
}
