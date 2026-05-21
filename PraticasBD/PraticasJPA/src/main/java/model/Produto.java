package model;

import jakarta.persistence.*;

@Entity //Isso diz ao JPA que esta classe será uma tabela no BD
@Table(name = "produtos")

public class Produto {

    @Id //Define a PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto-incremento
    private Long id;

    @Column(nullable = false) //Campo Obrigatório
    private String nome;

    private Double preco;
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
