package br.com.locadora.modelo;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCli")
    private Integer id;

    @Column(name = "nomCli", nullable = false, length = 100)
    private String nome;

    @Column(name = "emailCli", nullable = false, unique = true,length = 100)
    private String email;

    @Column(name = "telCli", length = 20)
    private String telefone;

    @Column(name = "senhaCli", nullable = false, length = 50)
    private String senha;

    public Cliente() {
    }

    public Cliente(String nome, String email, String telefone, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
