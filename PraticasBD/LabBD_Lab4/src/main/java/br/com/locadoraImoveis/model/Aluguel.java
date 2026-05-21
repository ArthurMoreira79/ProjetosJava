package br.com.locadoraImoveis.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "alugueis")
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluguel")
    private Integer id;

    @Column(name = "dt_vencimento")
    private LocalDate dtVencimento;

    @Column(name = "valor_pago", precision = 10, scale = 2)
    private BigDecimal valorPago;

    @Column(name = "obs", columnDefinition = "TEXT")
    private String obs;

    @ManyToOne
    @JoinColumn(name = "id_locacao")
    private Locacao locacao;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getDtVencimento() { return dtVencimento; }
    public void setDtVencimento(LocalDate dtVencimento) { this.dtVencimento = dtVencimento; }

    public BigDecimal getValorPago() { return valorPago; }
    public void setValorPago(BigDecimal valorPago) { this.valorPago = valorPago; }

    public String getObs() { return obs; }
    public void setObs(String obs) { this.obs = obs; }

    public Locacao getLocacao() { return locacao; }
    public void setLocacao(Locacao locacao) { this.locacao = locacao; }
}
