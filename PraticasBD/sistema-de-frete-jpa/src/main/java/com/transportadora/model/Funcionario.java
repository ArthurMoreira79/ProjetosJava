package com.transportadora.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "funcionario")
public class Funcionario extends PessoaFisica {
    @Column(unique = true, nullable = false)
    private int matricula;

    @Temporal(TemporalType.DATE)
    private Date dataAdmissao;

    private String cargo;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dependente> dependentes = new ArrayList<>();

    // Getters e Setters
    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = matricula; }
    public Date getDataAdmissao() { return dataAdmissao; }
    public void setDataAdmissao(Date dataAdmissao) { this.dataAdmissao = dataAdmissao; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public List<Dependente> getDependentes() { return dependentes; }
    public void setDependentes(List<Dependente> dependentes) { this.dependentes = dependentes; }
}