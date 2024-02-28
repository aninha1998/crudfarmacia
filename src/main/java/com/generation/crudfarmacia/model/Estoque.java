package com.generation.crudfarmacia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import io.swagger.v3.oas.annotations.media.Schema;



@Entity
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Produtos produtos;

    private int quantidade;

   

    public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estoque() {
    }

    public Estoque(Produtos produtos, int quantidade) {
        this.produtos = produtos;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }  

}