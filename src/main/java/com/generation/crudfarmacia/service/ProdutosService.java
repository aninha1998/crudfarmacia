package com.generation.crudfarmacia.service;

import com.generation.crudfarmacia.model.Produtos;
import com.generation.crudfarmacia.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository produtosRepository;


    public Optional<Produtos> obterProduto(Long id) {
        return produtosRepository.findById(id);
    }

    public Produtos criarProduto(Produtos produto) {
        return produtosRepository.save(produto);
    }

    public Produtos atualizarProduto(Long id, Produtos produtoAtualizado) {
        if (produtosRepository.existsById(id)) {
            produtoAtualizado.setId(id);
            return produtosRepository.save(produtoAtualizado);
        }
        return null;
    }

    public void deletarProduto(Long id) {
        produtosRepository.deleteById(id);
    }

    public List<Produtos> listarProdutosPorNome(String nome) {
        return produtosRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Produtos> listarProdutosPorCategoria(Long categoriaId) {
        return produtosRepository.findByCategoriaId(categoriaId);
    }
}
