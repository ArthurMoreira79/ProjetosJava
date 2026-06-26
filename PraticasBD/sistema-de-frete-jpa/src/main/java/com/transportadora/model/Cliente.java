package com.transportadora.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente extends PessoaFisica {
    private String contato;
    private boolean ativo;

    // Getters e Setters
    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}