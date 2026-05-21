package br.com.locadora.modelo;

import javax.persistence.*;

@Entity
@Table(name = "plataforma")
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlat")
    private Integer id;

    @Column(name = "nomPlat", nullable = false, length = 50)
    private String nome;

    public Plataforma() {
    }

    public Plataforma(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
