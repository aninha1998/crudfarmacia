package com.generation.crudfarmacia.service;

import com.generation.crudfarmacia.model.Medicamento;
import com.generation.crudfarmacia.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public List<Medicamento> getAllMedicamentos() {
        return medicamentoRepository.findAll();
    }

    public Medicamento getMedicamentoById(Long id) {
        Optional<Medicamento> medicamentoOptional = medicamentoRepository.findById(id);
        return medicamentoOptional.orElse(null);
    }

    public Medicamento createMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    public Medicamento updateMedicamento(Long id, Medicamento novoMedicamento) {
        if (!medicamentoRepository.existsById(id)) {
            return null;
        }
        novoMedicamento.setId(id);
        return medicamentoRepository.save(novoMedicamento);
    }

    public void deleteMedicamento(Long id) {
        medicamentoRepository.deleteById(id);
    }
}

