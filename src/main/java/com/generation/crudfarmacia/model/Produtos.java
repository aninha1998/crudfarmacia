package com.generation.crudfarmacia.model;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome é de preenchimento obrigatório")
    @Size(min = 3, max = 50, message = "O atributo nome deve ter no mínimo 3 caracteres e no máximo 50 caracteres")
    private String nome;

    @NotBlank(message = "O atributo tipo é de preenchimento obrigatório")
    private String tipo;

    @NotBlank(message = "O atributo descricao é de preenchimento obrigatório")
    @Size(min = 5, max = 200, message = "O atributo descricao deve ter no mínimo 5 caracteres e no máximo 200 caracteres")
    private String descricao;

    private int quantidadeEstoque;

    @ManyToOne
    private Categoria categoria;

    @UpdateTimestamp
    private LocalDateTime data;

    public Produtos() {}

    public Produtos(Long id, String nome, String tipo, String descricao, int quantidadeEstoque, Categoria categoria, LocalDateTime data) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
