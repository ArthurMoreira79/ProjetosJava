package br.com.locadora.modelo;

import br.com.locadora.dao.PlataformaDAO;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "jogo_plataforma")
public class JogoPlataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idJogo", nullable = false)
    private Jogo jogo;

    @ManyToOne
    @JoinColumn(name = "idPlat", nullable = false)
    private Plataforma plataforma;

    @Column(name = "precoDia", nullable = false, precision = 8, scale = 2)
    private BigDecimal precoDia;

    public JogoPlataforma() {
    }

    public JogoPlataforma(Jogo jogo, Plataforma plataforma, BigDecimal precoDia) {
        this.jogo = jogo;
        this.plataforma = plataforma;
        this.precoDia = precoDia;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Jogo getJogo() { return jogo; }
    public void setJogo(Jogo jogo) { this.jogo = jogo; }
    public Plataforma getPlataforma() { return plataforma; }
    public void setPlataforma(Plataforma plataforma) { this.plataforma = plataforma; }
    public BigDecimal getPrecoDia() { return precoDia; }
    public void setPrecoDia(BigDecimal precoDia) { this.precoDia = precoDia; }
}
