package com.generation.crudfarmacia;

import com.generation.crudfarmacia.model.Produtos;
import com.generation.crudfarmacia.service.ProdutosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProdutosControllerTest {

    @Autowired
    private ProdutosService produtosService;



    @Test
    public void testeObterProduto() {
        Optional<Produtos> produto = produtosService.obterProduto(1L);
        Assertions.assertTrue(produto.isPresent(), "Produto não encontrado");
        Assertions.assertEquals("Novo Produto", produto.get().getNome(), "Nome do produto incorreto");
    }

    @Test
    public void testeCriarProduto() {
        Produtos novoProduto = new Produtos();
        novoProduto.setNome("Novo Produto");
        novoProduto.setTipo("Tipo Teste");
        novoProduto.setDescricao("Descrição Teste");
        novoProduto.setQuantidadeEstoque(10);

        novoProduto = produtosService.criarProduto(novoProduto);
        Assertions.assertNotNull(novoProduto.getId(), "Falha ao criar o produto");
    }

    @Test
    public void testeAtualizarProduto() {
        Produtos produto = produtosService.obterProduto(1L).get();
        produto.setDescricao("Descrição Atualizada");
        produtosService.atualizarProduto(1L, produto);

        Produtos produtoAtualizado = produtosService.obterProduto(1L).orElse(null);
        Assertions.assertNotNull(produtoAtualizado, "Produto não encontrado após a atualização");
        Assertions.assertEquals("Descrição Atualizada", produtoAtualizado.getDescricao(), "Falha ao atualizar a descrição do produto");
    }

    @Test
    public void testeDeletarProduto() {
        produtosService.deletarProduto(2L);
        Assertions.assertFalse(produtosService.obterProduto(2L).isPresent(), "Falha ao deletar o produto");
    }

    @Test
    public void testeListarProdutosPorNome() {
        List<Produtos> produtos = produtosService.listarProdutosPorNome("Produto");
        Assertions.assertFalse(produtos.isEmpty(), "Nenhum produto encontrado com o nome informado");
    }

    @Test
    public void testeListarProdutosPorCategoria() {
        List<Produtos> produtos = produtosService.listarProdutosPorCategoria(1L);
        Assertions.assertTrue(produtos.isEmpty(), "Nenhum produto encontrado para a categoria informada");
    }
}

