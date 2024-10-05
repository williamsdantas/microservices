package com.aula.model;

import com.aula.dto.LivroDto;
import com.aula.enums.LivroStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_livros")
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 80, nullable = false)
	private String titulo;
	private String descricao;
	private Float nota;
	private Integer qtedPaginas;
	@Enumerated(EnumType.STRING)
	private LivroStatus livroStatus;
	
	public static Livro fromDto(LivroDto livroDto) {
		return new Livro(null, livroDto.titulo(), livroDto.descricao(), 
				livroDto.nota(), livroDto.qtdePaginas(), livroDto.livroStatus());
	}
}
