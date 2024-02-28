package com.generation.crudfarmacia;

import com.generation.crudfarmacia.model.Categoria;
import com.generation.crudfarmacia.service.CategoriaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CategoriaControllerTest {

    @Autowired
    private CategoriaService categoriaService;
    
    @Test
    public void testeListarPorCategoria() {
        List<Categoria> categorias = categoriaService.listarPorCategoria("Medicamentos");

        Assertions.assertTrue(categorias.isEmpty(), "Nenhuma categoria encontrada");

        if (!categorias.isEmpty()) {
            Assertions.assertEquals(1, categorias.size(), "Número de categorias incorreto");
            Assertions.assertEquals("Medicamentos", categorias.get(0).getNome(), "Nome da categoria incorreto");
        }
    }

    


    @Test
    public void testeObterCategoria() {
        Optional<Categoria> categoria = categoriaService.obterCategoria(1L);
        Assertions.assertTrue(categoria.isPresent(), "Categoria não encontrada");
        String nomeEsperado = "Higiene"; 
        Assertions.assertEquals(nomeEsperado, categoria.get().getNome(), "Nome da categoria incorreto");
    }

    

    @Test
    public void testeCriarCategoria() {
        Categoria novaCategoria = new Categoria();
        novaCategoria.setNome("Higiene");
        novaCategoria.setDescricao("Produtos de higiene pessoal");
        novaCategoria = categoriaService.criarCategoria(novaCategoria);
        Assertions.assertNotNull(novaCategoria.getId(), "Falha ao criar a categoria");
    }

    @Test
    public void testeAtualizarCategoria() {
        Categoria categoria = categoriaService.obterCategoria(1L).get();
        categoria.setDescricao("Medicamentos diversos");
        categoriaService.atualizarCategoria(1L, categoria);
        
        Categoria categoriaAtualizada = categoriaService.obterCategoria(1L).orElse(null);
        Assertions.assertNotNull(categoriaAtualizada, "Categoria não encontrada após a atualização");
        Assertions.assertEquals("Medicamentos diversos", categoriaAtualizada.getDescricao(), "Falha ao atualizar a descrição da categoria");
    }

    @Test
    public void testeDeletarCategoria() {
        categoriaService.deletarCategoria(2L);
        Assertions.assertFalse(categoriaService.obterCategoria(2L).isPresent(), "Falha ao deletar a categoria");
    }

    @Test
    public void testeBuscarPorNome() {
        List<Categoria> categoria = categoriaService.buscarPorNome("Medicamentos");
        Assertions.assertEquals(0, categoria.size(), "Falha ao buscar categorias por nome");
    }
}

