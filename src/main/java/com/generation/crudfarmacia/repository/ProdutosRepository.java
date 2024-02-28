package com.generation.crudfarmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.crudfarmacia.model.Produtos;

import java.util.List;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

    List<Produtos> findByNomeContainingIgnoreCase(String nome);

    List<Produtos> findByCategoriaId(Long categoriaId);
}
