package com.generation.crudfarmacia.service;

import com.generation.crudfarmacia.model.Categoria;
import com.generation.crudfarmacia.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarPorCategoria(String nome) {
        return categoriaRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Optional<Categoria> obterCategoria(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria criarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizarCategoria(Long id, Categoria categoriaAtualizada) {
        if (categoriaRepository.existsById(id)) {
            categoriaAtualizada.setId(id);
            return categoriaRepository.save(categoriaAtualizada);
        }
        return null;
    }

    public void deletarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    public List<Categoria> buscarPorNome(String nome) {
        return categoriaRepository.findByNomeContainingIgnoreCase(nome);
    }

	}

	

