package com.aula.dto;

import com.aula.model.Usuario;

public record UsuarioDto(
        Long id,
        String nome,
        String email,
        String senha,
        String telefone,
        String endereco) {

    public UsuarioDto(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(),null,
                usuario.getTelefone(), usuario.getEndereco());
    }
}

