package com.aula.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aula.dto.UsuarioDto;
import com.aula.model.Usuario;
import com.aula.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDto findById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id); 
        return usuario.map(UsuarioDto::new) 
                      .orElse(null); // Retorna null se não encontrado
    }


    @Transactional
    public UsuarioDto save(UsuarioDto usuarioDto) {
        Usuario usuario = Usuario.fromDto(usuarioDto); 
        return new UsuarioDto(usuarioRepository.save(usuario)); 
    }

    @Transactional
    public UsuarioDto update(Long id, UsuarioDto usuarioDto) {
        if (findById(id) != null){
            Usuario usuario = Usuario.fromDto(usuarioDto); 
            usuario.setId(id); 
            return new UsuarioDto(usuarioRepository.save(usuario)); 

        }
        return null;
        
    }
}
