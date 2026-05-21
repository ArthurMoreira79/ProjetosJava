package br.com.locadora.modelo;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "joog_locacao")
public class JogoLocacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idLoc", nullable = false)
    private Locacao locacao;

    @ManyToOne
    @JoinColumn(name = "igJogoPlat", nullable = false)
    private JogoPlataforma jogoPlataforma;

    @Column(name = "dias", nullable = false)
    private Integer dias;

    @Column(name = "qntd", nullable = false)
    private Integer quantidade;

    public JogoLocacao(){
        this.quantidade = 1;
    }

    public JogoLocacao(Locacao locacao, JogoPlataforma jogoPlataforma, Integer dias, Integer quantidade) {
        this.locacao = locacao;
        this.jogoPlataforma = jogoPlataforma;
        this.dias = dias;
        this.quantidade = quantidade;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Locacao getLocacao() { return locacao; }
    public void setLocacao(Locacao locacao) { this.locacao = locacao; }
    public JogoPlataforma getJogoPlataforma() { return jogoPlataforma; }
    public void setJogoPlataforma(JogoPlataforma jogoPlataforma) { this.jogoPlataforma = jogoPlataforma; }
    public Integer getDias() { return dias; }
    public void setDias(Integer dias) { this.dias = dias; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
}
