package com.generation.crudfarmacia.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String laboratorio;
    private BigDecimal preco;
    private String tarja; 
    
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicamento that = (Medicamento) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(laboratorio, that.laboratorio) &&
                Objects.equals(preco, that.preco) &&
                Objects.equals(tarja, that.tarja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, laboratorio, preco, tarja);
    }

    // Getters and Setters

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

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getTarja() {
        return tarja;
    }

    public void setTarja(String tarja) {
        this.tarja = tarja;
    }

    @Override
    public String toString() {
        return "Medicamento [id=" + id + ", nome=" + nome + ", laboratorio=" + laboratorio + "]";
    }
}
