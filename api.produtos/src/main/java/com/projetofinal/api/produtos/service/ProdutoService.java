package com.projetofinal.api.produtos.service;


import com.projetofinal.api.produtos.dto.ProdutoDto;
import com.projetofinal.api.produtos.model.Produto;
import com.projetofinal.api.produtos.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public ProdutoDto cadastrar(ProdutoDto produtoDto) {
        Produto produto = Produto.fromDto(produtoDto);
        return new ProdutoDto(produtoRepository.save(produto));

    }
}
