package br.com.locadora.modelo;

import javax.persistence.*;

@Entity
@Table(name = "acessorio")
public class Acessorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAce")
    private Integer id;

    @Column(name = "nomAce", nullable = false, length = 50)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "idCon", nullable = false)
    private Console console;

    public Acessorio() {}

    public Acessorio(String nome, Console console) {
        this.nome = nome;
        this.console = console;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Console getConsole() { return console; }
    public void setConsole(Console console) { this.console = console; }
}
