package com.transportadora.service;

import com.transportadora.dao.FreteDAO;
import com.transportadora.exception.FreteException;
import com.transportadora.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class FreteService {

    private static final BigDecimal VALOR_KM_RODADO = new BigDecimal("2.00");

    private final EntityManager em;
    private  final FreteDAO freteDAO;

    public FreteService(EntityManager em){
        this.em = em;
        this.freteDAO = new FreteDAO(em);
    }

    // --> TAREFA 1

    public Frete registrarFrete(Cliente cliente, Cidade cidadeOrigem, Cidade cidadeDestino,
                                Funcionario funcionarioResponsavel, CategoriaFrete categoriaFrete,
                                List<ItemFrete> itens){
        // Validações obrigatórias
        validarCliente(cliente);
        validarCidades(cidadeOrigem, cidadeDestino);
        validarItens(itens);
        validarFuncionario(funcionarioResponsavel);

        // Buscar distância cadastrada entre cidades
        Distancia distancia = buscarDistancia(cidadeOrigem, cidadeDestino);

        // Sugestão automática de veículo
        float pesoTotal = calcularPesoTotal(itens);
        Veiculo veiculo = selecionarVeiculoAutomatico(pesoTotal);

        // Montar o Frete
        Frete frete = new Frete(cliente, veiculo, cidadeOrigem, cidadeDestino, funcionarioResponsavel);
        frete.setValorKmRodado(VALOR_KM_RODADO);
        frete.setStatusFrete(StatusFrete.PENDENTE);
        frete.setCategoriaFrete(categoriaFrete);

        // Adicionar itens ao frete
        for (ItemFrete item: itens){
            frete.adicionarItem(item);
        }

        // Calcular o valor do frete
        BigDecimal valorCalculado = calcularValorFrete(frete, distancia);
        frete.setValorTotal(valorCalculado); //persiste o valor calculado

        // Persistir em transação
        em.getTransaction().begin();
        try{
            freteDAO.salvar(frete);
            frete.setCodigo(frete.getId().intValue());
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
            throw new FreteException("Erro ao persistir o frete: " + e.getMessage(), e);
        }
        return frete;
    }

    // --> TAREFA 2
    public BigDecimal calcularValorFrete(Frete frete, Distancia distancia) {

        //Validações
        if (frete == null) {
            throw new FreteException("Frete não pode ser nulo para o cálculo.");
        }
        if (distancia == null) {
            throw new FreteException("Distância não encontrada entre as cidades informadas.");
        }
        if (frete.getCidadeOrigem().getId().equals(frete.getCidadeDestino().getId())) {
            throw new FreteException("Cidade de origem e destino devem ser diferentes.");
        }
        if (frete.getItens() == null || frete.getItens().isEmpty()) {
            throw new FreteException("O frete deve ter pelo menos um item.");
        }

        //Calcular peso total dos itens
        float pesoTotal = calcularPesoTotal(frete.getItens());

        //Validar limite de peso do veículo
        if (frete.getVeiculo() != null && frete.getVeiculo().getPesoMaximo() != null) {
            if (pesoTotal > frete.getVeiculo().getPesoMaximo()) {
                throw new FreteException(String.format(
                        "Peso total dos itens (%.2f kg) excede a capacidade do veículo (%d kg).",
                        pesoTotal, frete.getVeiculo().getPesoMaximo()));
            }
        }

        // D × VKM
        BigDecimal valorBase = VALOR_KM_RODADO.multiply(new BigDecimal(distancia.getQuilometros()));

        // Adicional fixo da distância (AD)
        BigDecimal adicionalDistancia = distancia.getAdicionalKmRodado() != null
                ? distancia.getAdicionalKmRodado()
                : BigDecimal.ZERO;

        // (D × VKM) + AD
        BigDecimal totalBase = valorBase.add(adicionalDistancia);

        // P_peso — percentual adicional por faixa de peso
        BigDecimal percentualPeso = calcularPercentualPeso(pesoTotal);

        // P_categoria
        BigDecimal percentualCategoria = BigDecimal.ZERO;
        if (frete.getCategoriaFrete() != null) {
            percentualCategoria = BigDecimal.valueOf(frete.getCategoriaFrete().getPercentualAdicional())
                    .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
        }

        // ValorFrete = totalBase × (1 + P_peso) × (1 + P_categoria)
        BigDecimal fatorPeso      = BigDecimal.ONE.add(percentualPeso);
        BigDecimal fatorCategoria = BigDecimal.ONE.add(percentualCategoria);

        BigDecimal valorFinal = totalBase.multiply(fatorPeso).multiply(fatorCategoria)
                .setScale(2, RoundingMode.HALF_UP);

        return valorFinal;
    }

    // --> ATUALIZAÇÃO DE STATUS COM REGRAS DE NEGÓCIO

    public void atualizarStatus(Long freteId, StatusFrete novoStatus) {
        Frete frete = freteDAO.buscarPorId(freteId);
        if (frete == null) {
            throw new FreteException("Frete com id " + freteId + " não encontrado.");
        }

        StatusFrete statusAtual = frete.getStatusFrete();

        // Não permitir alteração de frete CANCELADO
        if (statusAtual == StatusFrete.CANCELADO) {
            throw new FreteException("Não é permitido alterar o status de um frete CANCELADO.");
        }

        // Não permitir retrocesso (usa ordinal da enum para comparar ordem)
        if (novoStatus.ordinal() < statusAtual.ordinal()) {
            throw new FreteException(String.format(
                    "Retrocesso de status não permitido: %s → %s.", statusAtual, novoStatus));
        }

        // Regra específica: EM_TRANSPORTE exige veículo e status PENDENTE anterior
        if (novoStatus == StatusFrete.EM_TRANSPORTE) {
            if (frete.getVeiculo() == null) {
                throw new FreteException("Transição para EM_TRANSPORTE exige um veículo associado ao frete.");
            }
            if (statusAtual != StatusFrete.PENDENTE) {
                throw new FreteException("Transição para EM_TRANSPORTE só é permitida a partir do status PENDENTE.");
            }
        }

        em.getTransaction().begin();
        try {
            frete.setStatusFrete(novoStatus);
            freteDAO.atualizar(frete);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new FreteException("Erro ao atualizar status do frete: " + e.getMessage(), e);
        }
    }

    // --> METODOS AUXILIARES

    private BigDecimal calcularPercentualPeso(float pesoTotal) {
        if (pesoTotal <= 100f) {
            return BigDecimal.ZERO;
        } else if (pesoTotal <= 300f) {
            return new BigDecimal("0.10"); // 10%
        } else {
            return new BigDecimal("0.20"); // 20%
        }
    }

    private float calcularPesoTotal(List<ItemFrete> itens) {
        if (itens == null) return 0f;
        float total = 0f;
        for (ItemFrete item : itens) {
            total += item.getPeso();
        }
        return total;
    }

    private Veiculo selecionarVeiculoAutomatico(float pesoTotal) {
        TypedQuery<Veiculo> query = em.createQuery(
                "SELECT v FROM Veiculo v WHERE v.pesoMaximo >= :peso ORDER BY v.pesoMaximo ASC",
                Veiculo.class);
        query.setParameter("peso", (int) Math.ceil(pesoTotal));
        query.setMaxResults(1);

        List<Veiculo> resultado = query.getResultList();
        if (resultado.isEmpty()) {
            throw new FreteException(String.format(
                    "Nenhum veículo disponível suporta o peso total de %.2f kg.", pesoTotal));
        }
        return resultado.get(0);
    }

    private Distancia buscarDistancia(Cidade origem, Cidade destino) {
        TypedQuery<Distancia> query = em.createQuery(
                "SELECT d FROM Distancia d WHERE " +
                        "(d.origem = :origem AND d.destino = :destino) OR " +
                        "(d.origem = :destino AND d.destino = :origem)",
                Distancia.class);
        query.setParameter("origem", origem);
        query.setParameter("destino", destino);
        query.setMaxResults(1);

        List<Distancia> resultado = query.getResultList();
        if (resultado.isEmpty()) {
            throw new FreteException(String.format(
                    "Distância não cadastrada entre %s e %s.",
                    origem.getNome(), destino.getNome()));
        }
        return resultado.get(0);
    }

    // VALIDAÇÕES DE NEGÓCIO

    private void validarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new FreteException("Cliente é obrigatório para registrar um frete.");
        }
        if (!cliente.isAtivo()) {
            throw new FreteException("O cliente informado está inativo e não pode solicitar fretes.");
        }
    }

    private void validarCidades(Cidade origem, Cidade destino) {
        if (origem == null || destino == null) {
            throw new FreteException("Cidade de origem e destino são obrigatórias.");
        }
        if (origem.getId().equals(destino.getId())) {
            throw new FreteException("Cidade de origem e destino devem ser diferentes.");
        }
    }

    private void validarItens(List<ItemFrete> itens) {
        if (itens == null || itens.isEmpty()) {
            throw new FreteException("O frete deve ter pelo menos um item de transporte.");
        }
    }

    private void validarFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            throw new FreteException("Funcionário responsável é obrigatório.");
        }
    }
}
