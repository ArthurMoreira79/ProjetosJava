package com.transportadora.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cidade")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uf;
    private String nome;
    private String estado;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}