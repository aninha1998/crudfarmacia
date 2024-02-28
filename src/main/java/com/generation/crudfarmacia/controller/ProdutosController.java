package com.generation.crudfarmacia.controller;

import com.generation.crudfarmacia.model.Produtos;
import com.generation.crudfarmacia.service.ProdutosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosService produtosService;


    @GetMapping("/{id}")
    public ResponseEntity<Produtos> obterProduto(@PathVariable Long id) {
        Optional<Produtos> produto = produtosService.obterProduto(id);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produtos> criarProduto(@RequestBody Produtos produto) {
        Produtos novoProduto = produtosService.criarProduto(produto);
        return ResponseEntity.ok(novoProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produtos> atualizarProduto(@PathVariable Long id, @RequestBody Produtos produtoAtualizado) {
        Produtos produto = produtosService.atualizarProduto(id, produtoAtualizado);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtosService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produtos>> listarProdutosPorNome(@PathVariable String nome) {
        List<Produtos> produtos = produtosService.listarProdutosPorNome(nome);
        return produtos.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(produtos);
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Produtos>> listarProdutosPorCategoria(@PathVariable Long categoriaId) {
        List<Produtos> produtos = produtosService.listarProdutosPorCategoria(categoriaId);
        return produtos.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(produtos);
    }
}
