package com.transportadora.model;

import jakarta.persistence.*;

@Entity
@Table(name = "item_frete")
public class ItemFrete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private float peso;

    @ManyToOne(optional = false)
    @JoinColumn(name = "frete_id", nullable = false)
    private Frete frete;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public float getPeso() { return peso; }
    public void setPeso(float peso) { this.peso = peso; }
    public Frete getFrete() { return frete; }
    public void setFrete(Frete frete) { this.frete = frete; }
}