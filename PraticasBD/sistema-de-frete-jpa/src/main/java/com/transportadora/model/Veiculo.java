package com.transportadora.model;

import jakarta.persistence.*;

@Entity
@Table(name = "veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroPlaca;
    private Integer pesoMaximo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "filial_id", nullable = false)
    private Filial filial;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_veiculo_id", nullable = false)
    private TipoVeiculo tipoVeiculo;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumeroPlaca() { return numeroPlaca; }
    public void setNumeroPlaca(String numeroPlaca) { this.numeroPlaca = numeroPlaca; }
    public Integer getPesoMaximo() { return pesoMaximo; }
    public void setPesoMaximo(Integer pesoMaximo) { this.pesoMaximo = pesoMaximo; }
    public Filial getFilial() { return filial; }
    public void setFilial(Filial filial) { this.filial = filial; }
    public TipoVeiculo getTipoVeiculo() { return tipoVeiculo; }
    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) { this.tipoVeiculo = tipoVeiculo; }
}