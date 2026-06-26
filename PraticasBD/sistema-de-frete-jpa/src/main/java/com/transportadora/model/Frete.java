package com.transportadora.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "frete")
public class Frete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private int codigo;
    private int numeroNotaFiscal;
    private BigDecimal valorKmRodado;

    // Valor final do frete, calculado pela camada de serviço (FreteService.calcularValorFrete).
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusFrete statusFrete;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cidade_origem_id", nullable = false)
    private Cidade cidadeOrigem;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cidade_destino_id", nullable = false)
    private Cidade cidadeDestino;

    @ManyToOne(optional = false)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionarioResponsavel;

    @ManyToOne
    @JoinColumn(name = "categoria_frete_id")
    private CategoriaFrete categoriaFrete;

    @OneToMany(mappedBy = "frete", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemFrete> itens = new ArrayList<>();

    // Construtor com validação de regras de integridade do domínio
    public Frete(Cliente cliente, Veiculo veiculo, Cidade origem, Cidade destino, Funcionario responsavel) {
        if (cliente == null || veiculo == null || origem == null || destino == null || responsavel == null) {
            throw new IllegalArgumentException("Todos os relacionamentos base devem ser preenchidos.");
        }
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.cidadeOrigem = origem;
        this.cidadeDestino = destino;
        this.funcionarioResponsavel = responsavel;
    }

    protected Frete() {}

    public void adicionarItem(ItemFrete item) {
        itens.add(item);
        item.setFrete(this);
    }

    // O calculo do frete agora mora no frete service

    // Getters e Setters adicionais
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }
    public int getNumeroNotaFiscal() { return numeroNotaFiscal; }
    public void setNumeroNotaFiscal(int numeroNotaFiscal) { this.numeroNotaFiscal = numeroNotaFiscal; }
    public BigDecimal getValorKmRodado() { return valorKmRodado; }
    public void setValorKmRodado(BigDecimal valorKmRodado) { this.valorKmRodado = valorKmRodado; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public void setStatusFrete(StatusFrete novoStatus) {
        if (novoStatus == null) {
            throw new IllegalArgumentException("O novo status do frete não pode ser nulo.");
        }
        // Permite a primeira atribuição (objeto recém criado)
        if (this.statusFrete != null && !this.statusFrete.podeMudarPara(novoStatus)) {
            throw new IllegalArgumentException(
                    "Mudança de status inválida! Não é permitido retroceder o status de "
                            + this.statusFrete + " para " + novoStatus + "."
            );
        }
        this.statusFrete = novoStatus;
    }

    public StatusFrete getStatusFrete() { return statusFrete; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }
    public Cidade getCidadeOrigem() { return cidadeOrigem; }
    public void setCidadeOrigem(Cidade cidadeOrigem) { this.cidadeOrigem = cidadeOrigem; }
    public Cidade getCidadeDestino() { return cidadeDestino; }
    public void setCidadeDestino(Cidade cidadeDestino) { this.cidadeDestino = cidadeDestino; }
    public Funcionario getFuncionarioResponsavel() { return funcionarioResponsavel; }
    public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) { this.funcionarioResponsavel = funcionarioResponsavel; }
    public CategoriaFrete getCategoriaFrete() { return categoriaFrete; }
    public void setCategoriaFrete(CategoriaFrete categoriaFrete) { this.categoriaFrete = categoriaFrete; }
    public List<ItemFrete> getItens() { return itens; }
    public void setItens(List<ItemFrete> itens) { this.itens = itens; }
}