package br.com.locadoraImoveis.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cli")
    private Integer id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "cpf", nullable = false, length = 255)
    private String cpf;

    @Column(name = "telefone1", nullable = false, length = 10)
    private String telefone1;

    @Column(name = "telefone2", length = 10)
    private String telefone2;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "dt_nascimento", nullable = false)
    private LocalDate dtNascimento;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone1() { return telefone1; }
    public void setTelefone1(String telefone1) { this.telefone1 = telefone1; }

    public String getTelefone2() { return telefone2; }
    public void setTelefone2(String telefone2) { this.telefone2 = telefone2; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getDtNascimento() { return dtNascimento; }
    public void setDtNascimento(LocalDate dtNascimento) { this.dtNascimento = dtNascimento; }
}
