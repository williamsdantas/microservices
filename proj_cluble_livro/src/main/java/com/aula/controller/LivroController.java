package com.aula.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aula.dto.LivroDto;
import com.aula.service.LivroService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/livros",
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
public class LivroController {
	
	private final LivroService livroService;
	
	@GetMapping
	public ResponseEntity<Page<LivroDto>> findAll(@PageableDefault(size= 10) Pageable pagination){
		return ResponseEntity.ok(livroService.findAll(pagination));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LivroDto> findById(@PathVariable Long id){
		return ResponseEntity.ok(livroService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<LivroDto> save(@RequestBody LivroDto livroDto){
		return ResponseEntity.status(201).body(livroService.save(livroDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<LivroDto> update(@PathVariable Long id, @RequestBody LivroDto livroDto){
		return ResponseEntity.ok(livroService.update(id, livroDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		livroService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}/updateStatus")
	public ResponseEntity<LivroDto> updateStatus(@PathVariable Long id, 
			@RequestBody Map<String, String> body){
		
		String status = body.get("status");
		return ResponseEntity.ok(livroService.updateStatus(id, status));
	}
	
}
