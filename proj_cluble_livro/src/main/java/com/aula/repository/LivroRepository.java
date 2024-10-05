package com.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aula.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
