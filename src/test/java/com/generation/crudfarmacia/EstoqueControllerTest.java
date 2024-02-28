package com.generation.crudfarmacia;

import com.generation.crudfarmacia.controller.EstoqueController;
import com.generation.crudfarmacia.model.Estoque;
import com.generation.crudfarmacia.service.EstoqueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstoqueControllerTest {

    @Mock
    private EstoqueService estoqueService;

    @InjectMocks
    private EstoqueController estoqueController;

    @Test
    void getAllEstoques() {
        // Given
        Estoque estoque = new Estoque();
        List<Estoque> estoques = Collections.singletonList(estoque);

        // When
        when(estoqueService.getAllEstoques()).thenReturn(estoques);

        // Then
        ResponseEntity<List<Estoque>> responseEntity = estoqueController.getAllEstoques();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(estoques, responseEntity.getBody());
    }

    @Test
    void getEstoqueById() {
        // Given
        Long id = 1L;
        Estoque estoque = new Estoque();
        estoque.setId(id);

        // When
        when(estoqueService.getEstoqueById(id)).thenReturn(estoque);

        // Then
        ResponseEntity<Estoque> responseEntity = estoqueController.getEstoqueById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(estoque, responseEntity.getBody());
    }

    @Test
    void addEstoque() {
        // Given
        Estoque estoque = new Estoque();
        when(estoqueService.addEstoque(estoque)).thenReturn(estoque);

        // When
        ResponseEntity<Estoque> responseEntity = estoqueController.addEstoque(estoque);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(estoque, responseEntity.getBody());
    }

    @Test
    void updateEstoque() {
        // Given
        Long id = 1L;
        Estoque estoque = new Estoque();
        estoque.setId(id);

        // When
        when(estoqueService.updateEstoque(id, estoque)).thenReturn(estoque);

        // Then
        ResponseEntity<Estoque> responseEntity = estoqueController.updateEstoque(id, estoque);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(estoque, responseEntity.getBody());
    }

    @Test
    void deleteEstoque() {
        // Given
        Long id = 1L;

        // When
        ResponseEntity<Void> responseEntity = estoqueController.deleteEstoque(id);

        // Then
        verify(estoqueService, times(1)).deleteEstoque(id);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
