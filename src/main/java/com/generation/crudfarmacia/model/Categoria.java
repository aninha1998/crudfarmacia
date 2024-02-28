package com.generation.crudfarmacia.model;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome é de preenchimento obrigatório")
    @Size(min = 3, max = 50, message = "O atributo nome deve ter no mínimo 3 caracteres e no máximo 50 caracteres")
    private String nome;

    @NotBlank(message = "O atributo descricao é de preenchimento obrigatório")
    @Size(min = 5, max = 200, message = "O atributo descricao deve ter no mínimo 5 caracteres e no máximo 200 caracteres")
    private String descricao;

    @UpdateTimestamp
    private LocalDateTime data;


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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
