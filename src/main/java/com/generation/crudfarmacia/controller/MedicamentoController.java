package com.generation.crudfarmacia.controller;

import com.generation.crudfarmacia.model.Medicamento;
import com.generation.crudfarmacia.service.MedicamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @GetMapping
    public ResponseEntity<List<Medicamento>> getAllMedicamentos() {
        List<Medicamento> medicamentos = medicamentoService.getAllMedicamentos();
        return ResponseEntity.ok(medicamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> getMedicamentoById(@PathVariable("id") Long id) {
        Medicamento medicamento = medicamentoService.getMedicamentoById(id);
        return ResponseEntity.ok(medicamento);
    }

    @PostMapping
    public ResponseEntity<Medicamento> createMedicamento(@Valid @RequestBody Medicamento medicamento) {
        Medicamento createdMedicamento = medicamentoService.createMedicamento(medicamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMedicamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> updateMedicamento(@PathVariable("id") Long id, @Valid @RequestBody Medicamento medicamento) {
        Medicamento updatedMedicamento = medicamentoService.updateMedicamento(id, medicamento);
        return ResponseEntity.ok(updatedMedicamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicamento(@PathVariable("id") Long id) {
        medicamentoService.deleteMedicamento(id);
        return ResponseEntity.noContent().build();
    }
}

