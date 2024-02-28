package com.generation.crudfarmacia.controller;

import com.generation.crudfarmacia.model.Categoria;
import com.generation.crudfarmacia.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/listarPorCategoria/{nome}")
    public ResponseEntity<List<Categoria>> listarPorCategoria(@PathVariable String nome) {
        List<Categoria> categorias = categoriaService.listarPorCategoria(nome);
        return categorias.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obterCategoria(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.obterCategoria(id);
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaService.criarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaAtualizada) {
        Categoria categoria = categoriaService.atualizarCategoria(id, categoriaAtualizada);
        return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Categoria>> buscarPorNome(@PathVariable String nome) {
        List<Categoria> categoria = categoriaService.buscarPorNome(nome);
        return categoria.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(categoria);
    }
}
