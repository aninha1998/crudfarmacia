package com.generation.crudfarmacia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generation.crudfarmacia.controller.MedicamentoController;
import com.generation.crudfarmacia.model.Medicamento;
import com.generation.crudfarmacia.service.MedicamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MedicamentoController.class)
class MedicamentoControllerTest {

    @Mock
    private MedicamentoService medicamentoService;

    @InjectMocks
    private MedicamentoController medicamentoController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(medicamentoController).build();
    }

    @Test
    void getAllMedicamentos() throws Exception {
        Medicamento medicamento1 = new Medicamento();
        Medicamento medicamento2 = new Medicamento();
        medicamento1.setNome("Medicamento1");
        medicamento2.setNome("Medicamento2");
        List<Medicamento> medicamentos = Arrays.asList(medicamento1, medicamento2);

        when(medicamentoService.getAllMedicamentos()).thenReturn(medicamentos);

        mockMvc.perform(get("/medicamentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nome").value("Medicamento1"))
                .andExpect(jsonPath("$[1].nome").value("Medicamento2"));
    }

    @Test
    void getMedicamentoById() throws Exception {
        Medicamento medicamento = new Medicamento();
        medicamento.setNome("Medicamento1");
        medicamento.setId(1L);

        when(medicamentoService.getMedicamentoById(1L)).thenReturn(medicamento);

        mockMvc.perform(get("/medicamentos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Medicamento1"));
    }

    @Test
    void createMedicamento() throws Exception {
        Medicamento medicamento = new Medicamento();
        medicamento.setNome("Novo Medicamento");

        when(medicamentoService.createMedicamento(any(Medicamento.class))).thenReturn(medicamento);

        mockMvc.perform(post("/medicamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(medicamento)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Novo Medicamento"));
    }

    @Test
    void updateMedicamento() throws Exception {
        Medicamento medicamento = new Medicamento();
        medicamento.setNome("Medicamento1");
        medicamento.setId(1L);

        when(medicamentoService.updateMedicamento(any(Long.class), any(Medicamento.class))).thenReturn(medicamento);

        mockMvc.perform(put("/medicamentos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(medicamento)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Medicamento1"));
    }

    @Test
    void deleteMedicamento() throws Exception {
        mockMvc.perform(delete("/medicamentos/1"))
                .andExpect(status().isNoContent());
    }
}

