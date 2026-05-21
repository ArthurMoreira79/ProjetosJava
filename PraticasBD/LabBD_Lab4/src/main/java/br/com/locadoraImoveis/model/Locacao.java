package br.com.locadoraImoveis.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "locacao")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loc")
    private Integer id;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "dia_vencimento")
    private Integer diaVencimento;

    @Column(name = "perc_multa", precision = 10, scale = 2)
    private BigDecimal percMulta;

    @Column(name = "valor_aluguel", precision = 10, scale = 2)
    private BigDecimal valorAluguel;

    @Column(name = "obs", columnDefinition = "TEXT")
    private String obs;

    @ManyToOne
    @JoinColumn(name = "id_imovel")
    private Imovel imovel;

    @ManyToOne
    @JoinColumn(name = "id_inquilino")
    private Cliente inquilino;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public Integer getDiaVencimento() { return diaVencimento; }
    public void setDiaVencimento(Integer diaVencimento) { this.diaVencimento = diaVencimento; }

    public BigDecimal getPercMulta() { return percMulta; }
    public void setPercMulta(BigDecimal percMulta) { this.percMulta = percMulta; }

    public BigDecimal getValorAluguel() { return valorAluguel; }
    public void setValorAluguel(BigDecimal valorAluguel) { this.valorAluguel = valorAluguel; }

    public String getObs() { return obs; }
    public void setObs(String obs) { this.obs = obs; }

    public Imovel getImovel() { return imovel; }
    public void setImovel(Imovel imovel) { this.imovel = imovel; }

    public Cliente getInquilino() { return inquilino; }
    public void setInquilino(Cliente inquilino) { this.inquilino = inquilino; }
}
