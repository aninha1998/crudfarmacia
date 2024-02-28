package com.generation.crudfarmacia.controller;

import com.generation.crudfarmacia.model.Estoque;
import com.generation.crudfarmacia.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    @Autowired
    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> getAllEstoques() {
        List<Estoque> estoques = estoqueService.getAllEstoques();
        return new ResponseEntity<>(estoques, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estoque> getEstoqueById(@PathVariable("id") Long id) {
        Estoque estoque = estoqueService.getEstoqueById(id);
        return new ResponseEntity<>(estoque, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Estoque> addEstoque(@RequestBody Estoque estoque) {
        Estoque newEstoque = estoqueService.addEstoque(estoque);
        return new ResponseEntity<>(newEstoque, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estoque> updateEstoque(@PathVariable("id") Long id, @RequestBody Estoque estoque) {
        Estoque updatedEstoque = estoqueService.updateEstoque(id, estoque);
        return new ResponseEntity<>(updatedEstoque, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstoque(@PathVariable("id") Long id) {
        estoqueService.deleteEstoque(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

