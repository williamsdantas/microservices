package com.projetofinal.api.produtos.model;

import com.projetofinal.api.produtos.dto.ProdutoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Integer quantidade;
    @Column(precision = 10, scale = 2)
    private BigDecimal preco;

    public static Produto fromDto(ProdutoDto produtoDto) {
        return new Produto(null, produtoDto.nome(), produtoDto.descricao(), produtoDto.quantidade(),
                produtoDto.preco());
    }

}
