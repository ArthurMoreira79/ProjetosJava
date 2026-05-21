package br.com.locadoraImoveis.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_imovel")
public class TipoImovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_imv")
    private Integer id;

    @Column(name = "descricao", length = 100)
    private String descricao;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
