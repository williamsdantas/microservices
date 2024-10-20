package com.projetofinal.api.produtos.dto;

import com.projetofinal.api.produtos.model.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoDto(
        Long id,
        @NotBlank String nome,
        @NotBlank String descricao,
        @NotNull Integer quantidade,
        @NotNull BigDecimal preco
    )
{
    public ProdutoDto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getQuantidade(),
                produto.getPreco());
    }

}
