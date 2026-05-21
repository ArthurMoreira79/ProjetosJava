package br.com.locadora.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cliente_console")
public class ClienteConsole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idCli", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idCon", nullable = false)
    private Console console;

    @Column(name = "dataCliCon", nullable = false)
    private LocalDate dataUso;

    @Column(name = "qntHoras", nullable = false)
    private Integer quantidadeHoras;

    @Column(name = "valTotal", nullable = false, precision = 8, scale = 2)
    private BigDecimal valorTotal;

    public ClienteConsole() {
        this.dataUso = LocalDate.now(); // Pega a data atual por padrão
    }

    public ClienteConsole(Cliente cliente, Console console, Integer quantidadeHoras, BigDecimal valorTotal) {
        this.cliente = cliente;
        this.console = console;
        this.quantidadeHoras = quantidadeHoras;
        this.valorTotal = valorTotal;
        this.dataUso = LocalDate.now();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Console getConsole() { return console; }
    public void setConsole(Console console) { this.console = console; }
    public LocalDate getDataUso() { return dataUso; }
    public void setDataUso(LocalDate dataUso) { this.dataUso = dataUso; }
    public Integer getQuantidadeHoras() { return quantidadeHoras; }
    public void setQuantidadeHoras(Integer quantidadeHoras) { this.quantidadeHoras = quantidadeHoras; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
}
