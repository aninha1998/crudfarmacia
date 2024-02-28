package com.generation.crudfarmacia.repository;

import com.generation.crudfarmacia.model.Categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	List<Categoria> findByNome(String nome);

	List<Categoria> findByNomeContainingIgnoreCase(String nome);

 
}
