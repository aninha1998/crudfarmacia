package com.generation.crudfarmacia.service;

import com.generation.crudfarmacia.model.Estoque;
import com.generation.crudfarmacia.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    @Autowired
    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public List<Estoque> getAllEstoques() {
        return estoqueRepository.findAll();
    }

    public Estoque getEstoqueById(Long id) {
        Optional<Estoque> optionalEstoque = estoqueRepository.findById(id);
        return optionalEstoque.orElse(null);
    }

    public Estoque addEstoque(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public Estoque updateEstoque(Long id, Estoque estoque) {
        if (estoqueRepository.existsById(id)) {
            estoque.setId(id);
            return estoqueRepository.save(estoque);
        }
        return null;
    }

    public void deleteEstoque(Long id) {
        estoqueRepository.deleteById(id);
    }
}
