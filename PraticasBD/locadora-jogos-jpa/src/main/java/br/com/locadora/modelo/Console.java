package br.com.locadora.modelo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "console")
public class Console {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCon")
    private Integer id;

    @Column(name = "nomCon", nullable = false, length = 100)
    private String nome;

    @Column(name = "preHor", nullable = false, precision = 8, scale = 2)
    private BigDecimal precoPorHora;

    public Console() {}

    public Console(String nome, BigDecimal precoPorHora) {
        this.nome = nome;
        this.precoPorHora = precoPorHora;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public BigDecimal getPrecoPorHora() { return precoPorHora; }
    public void setPrecoPorHora(BigDecimal precoPorHora) { this.precoPorHora = precoPorHora; }
}

