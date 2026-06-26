package com.transportadora.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "distancia", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"origem_id", "destino_id"})
})
public class Distancia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quilometros;
    private BigDecimal adicionalKmRodado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "origem_id")
    private Cidade origem; // Mapeado como origem no banco

    @ManyToOne(optional = false)
    @JoinColumn(name = "destino_id")
    private Cidade destino;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getQuilometros() { return quilometros; }
    public void setQuilometros(int quilometros) { this.quilometros = quilometros; }
    public BigDecimal getAdicionalKmRodado() { return adicionalKmRodado; }
    public void setAdicionalKmRodado(BigDecimal adicionalKmRodado) { this.adicionalKmRodado = adicionalKmRodado; }
    public Cidade getOrigem() { return origem; }
    public void setOrigem(Cidade origem) { this.origem = origem; }
    public Cidade getDestino() { return destino; }
    public void setDestino(Cidade destino) { this.destino = destino; }
}