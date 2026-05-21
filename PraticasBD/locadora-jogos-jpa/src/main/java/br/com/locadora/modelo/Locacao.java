package br.com.locadora.modelo;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;

@Entity
@Table(name = "locacao")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLoc")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idCli", nullable = false)
    private Cliente cliente;

    @Column(name = "dataLoc", nullable = false)
    private LocalDate dataLocacao;

    public Locacao(){
        this.dataLocacao = LocalDate.now();
    }

    public Locacao(Cliente cliente) {
        this.cliente = cliente;
        this.dataLocacao = LocalDate.now();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public LocalDate getDataLocacao() { return dataLocacao; }
    public void setDataLocacao(LocalDate dataLocacao) { this.dataLocacao = dataLocacao; }
}
