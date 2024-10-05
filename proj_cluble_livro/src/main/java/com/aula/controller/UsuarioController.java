package com.aula.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aula.dto.LivroDto;
import com.aula.dto.UsuarioDto;
import com.aula.service.UsuarioService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/users",
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> findById(@PathVariable Long id){
		return ResponseEntity.ok(usuarioService.findById(id));
	}

    @PostMapping
	public ResponseEntity<UsuarioDto> save(@RequestBody UsuarioDto usuarioDto){
		return ResponseEntity.status(201).body(usuarioService.save(usuarioDto));
	}

    @PutMapping("/{id}")
	public ResponseEntity<UsuarioDto> update(@PathVariable Long id, @RequestBody UsuarioDto usuarioDto){
        UsuarioDto achouUsuario = usuarioService.update(id, usuarioDto);

        if(achouUsuario != null){
            return ResponseEntity.ok(achouUsuario);
        }
        return ResponseEntity.notFound().build(); 
	}


    
}
