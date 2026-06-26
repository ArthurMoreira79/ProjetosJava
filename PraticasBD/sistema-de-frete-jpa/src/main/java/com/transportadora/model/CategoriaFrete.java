package com.transportadora.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria_frete")
public class CategoriaFrete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private float percentualAdicional;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public float getPercentualAdicional() { return percentualAdicional; }
    public void setPercentualAdicional(float percentualAdicional) { this.percentualAdicional = percentualAdicional; }
}