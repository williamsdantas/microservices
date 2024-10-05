package com.aula.dto;

import com.aula.enums.LivroStatus;
import com.aula.model.Livro;

public record LivroDto(
		Long id, 
		String titulo, 
		String descricao, 
		Float nota, 
		Integer qtdePaginas, 
		LivroStatus livroStatus) {
	
	public LivroDto(Livro livro) {
		this(livro.getId(), livro.getTitulo(), livro.getDescricao(), 
				livro.getNota(), livro.getQtedPaginas(), livro.getLivroStatus());
	}

}
