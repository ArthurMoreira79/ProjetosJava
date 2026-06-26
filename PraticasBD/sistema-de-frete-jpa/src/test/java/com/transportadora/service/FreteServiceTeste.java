package com.transportadora.service;

import com.transportadora.exception.FreteException;
import com.transportadora.model.*;
import com.transportadora.util.JPAUtil;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FreteServiceTeste {

    private static EntityManager em;
    private static FreteService freteService;

    //Obj compartilhados entre testes

    private static Cliente clienteAtivo;
    private static Cliente clienteInativo;
    private static Funcionario funcionario;
    private static Cidade cidadeSaoLuis;
    private static Cidade cidadeFortaleza;
    private static Cidade cidadeBelem;        // sem distância cadastrada com São Luís
    private static Distancia distanciaSlFor;  // São Luís → Fortaleza
    private static Veiculo veiculoPequeno;    // pesoMaximo = 200 kg
    private static Veiculo veiculoGrande;     // pesoMaximo = 1000 kg
    private static CategoriaFrete categoriaRapida;   // percentualAdicional = 10%
    private static CategoriaFrete categoriaNormal;    // percentualAdicional = 0%

    // Setup e Teardown

    @BeforeAll
    static void configurar() {
        em = JPAUtil.getEntityManager();
        freteService = new FreteService(em);
        carregarDadosDosBanco();
    }

    @AfterAll
    static void encerrar() {
        if (em != null && em.isOpen()) em.close();
    }


    // Busca as entidades já persistidas no banco para uso nos testes.

    private static void carregarDadosDosBanco() {
        clienteAtivo = em.createQuery(
                        "SELECT c FROM Cliente c WHERE c.ativo = true", Cliente.class)
                .setMaxResults(1).getSingleResult();

        // Busca ou cria um cliente inativo para testes negativos
        List<Cliente> inativos = em.createQuery(
                        "SELECT c FROM Cliente c WHERE c.ativo = false", Cliente.class)
                .setMaxResults(1).getResultList();
        clienteInativo = inativos.isEmpty() ? null : inativos.get(0);

        funcionario = em.createQuery(
                        "SELECT f FROM Funcionario f", Funcionario.class)
                .setMaxResults(1).getSingleResult();

        List<Cidade> cidades = em.createQuery(
                        "SELECT c FROM Cidade c ORDER BY c.id", Cidade.class)
                .setMaxResults(3).getResultList();
        cidadeSaoLuis  = cidades.get(0);
        cidadeFortaleza = cidades.get(1);
        cidadeBelem    = cidades.size() > 2 ? cidades.get(2) : null;

        distanciaSlFor = em.createQuery(
                        "SELECT d FROM Distancia d WHERE " +
                                "(d.origem = :o AND d.destino = :d) OR (d.origem = :d AND d.destino = :o)",
                        Distancia.class)
                .setParameter("o", cidadeSaoLuis)
                .setParameter("d", cidadeFortaleza)
                .setMaxResults(1).getSingleResult();

        List<Veiculo> veiculos = em.createQuery(
                        "SELECT v FROM Veiculo v ORDER BY v.pesoMaximo ASC", Veiculo.class)
                .setMaxResults(2).getResultList();
        veiculoPequeno = veiculos.get(0);
        veiculoGrande  = veiculos.size() > 1 ? veiculos.get(1) : veiculos.get(0);

        categoriaRapida = em.createQuery(
                        "SELECT c FROM CategoriaFrete c WHERE c.percentualAdicional > 0", CategoriaFrete.class)
                .setMaxResults(1).getSingleResult();

        categoriaNormal = em.createQuery(
                        "SELECT c FROM CategoriaFrete c WHERE c.percentualAdicional = 0", CategoriaFrete.class)
                .setMaxResults(1).getSingleResult();
    }

    // TAREFA 1 - REGISTRAR FRETE

    @Test
    @Order(1)
    @DisplayName("T01 - Deve registrar frete com sucesso e status PENDENTE")
    void deveRegistrarFreteComSucesso() {
        List<ItemFrete> itens = criarItens("Caixa de eletrônicos", 50f);

        Frete frete = freteService.registrarFrete(
                clienteAtivo, cidadeSaoLuis, cidadeFortaleza,
                funcionario, categoriaNormal, itens);

        assertNotNull(frete.getId(), "Frete deve ter ID gerado após persistência");
        assertEquals(StatusFrete.PENDENTE, frete.getStatusFrete(), "Status inicial deve ser PENDENTE");
        assertNotNull(frete.getVeiculo(), "Veículo deve ter sido selecionado automaticamente");
        assertNotNull(frete.getValorTotal(), "Valor do frete deve ter sido calculado");
        assertTrue(frete.getValorTotal().compareTo(BigDecimal.ZERO) > 0, "Valor do frete deve ser positivo");

        System.out.println("[T01] Frete registrado com sucesso. ID: " + frete.getId()
                + " | Valor: R$ " + frete.getValorTotal());
    }

    @Test
    @Order(2)
    @DisplayName("T02 - Deve registrar frete com categoria de entrega rápida")
    void deveRegistrarFreteComCategoriaRapida() {
        List<ItemFrete> itens = criarItens("Móvel pequeno", 80f);

        Frete freteNormal = freteService.registrarFrete(
                clienteAtivo, cidadeSaoLuis, cidadeFortaleza,
                funcionario, categoriaNormal, itens);

        List<ItemFrete> itens2 = criarItens("Móvel pequeno", 80f);
        Frete freteRapido = freteService.registrarFrete(
                clienteAtivo, cidadeSaoLuis, cidadeFortaleza,
                funcionario, categoriaRapida, itens2);

        assertTrue(
                freteRapido.getValorTotal().compareTo(freteNormal.getValorTotal()) > 0,
                "Frete rápido deve ser mais caro que o normal");

        System.out.println("[T02] Normal: R$ " + freteNormal.getValorTotal()
                + " | Rápido: R$ " + freteRapido.getValorTotal());
    }

    @Test
    @Order(3)
    @DisplayName("T03 - Deve lançar exceção ao registrar frete sem cliente")
    void deveLancarExcecaoSemCliente() {
        List<ItemFrete> itens = criarItens("Item teste", 10f);

        FreteException ex = assertThrows(FreteException.class, () ->
                freteService.registrarFrete(
                        null, cidadeSaoLuis, cidadeFortaleza,
                        funcionario, categoriaNormal, itens));

        assertTrue(ex.getMessage().contains("Cliente"), ex.getMessage());
        System.out.println("[T03] Exceção correta: " + ex.getMessage());
    }

    @Test
    @Order(4)
    @DisplayName("T04 - Deve lançar exceção com cliente inativo")
    void deveLancarExcecaoClienteInativo() {
        Assumptions.assumeTrue(clienteInativo != null,
                "Pulando: nenhum cliente inativo cadastrado no banco.");

        List<ItemFrete> itens = criarItens("Item teste", 10f);

        FreteException ex = assertThrows(FreteException.class, () ->
                freteService.registrarFrete(
                        clienteInativo, cidadeSaoLuis, cidadeFortaleza,
                        funcionario, categoriaNormal, itens));

        assertTrue(ex.getMessage().toLowerCase().contains("inativo"), ex.getMessage());
        System.out.println("[T04] Exceção correta: " + ex.getMessage());
    }

    @Test
    @Order(5)
    @DisplayName("T05 - Deve lançar exceção quando origem igual ao destino")
    void deveLancarExcecaoCidadesIguais() {
        List<ItemFrete> itens = criarItens("Item teste", 10f);

        FreteException ex = assertThrows(FreteException.class, () ->
                freteService.registrarFrete(
                        clienteAtivo, cidadeSaoLuis, cidadeSaoLuis,
                        funcionario, categoriaNormal, itens));

        assertTrue(ex.getMessage().toLowerCase().contains("diferente")
                || ex.getMessage().toLowerCase().contains("igual"), ex.getMessage());
        System.out.println("[T05] Exceção correta: " + ex.getMessage());
    }

    @Test
    @Order(6)
    @DisplayName("T06 - Deve lançar exceção quando não há distância cadastrada")
    void deveLancarExcecaoSemDistancia() {
        Assumptions.assumeTrue(cidadeBelem != null,
                "Pulando: precisa de uma terceira cidade sem distância cadastrada.");

        List<ItemFrete> itens = criarItens("Item teste", 10f);

        FreteException ex = assertThrows(FreteException.class, () ->
                freteService.registrarFrete(
                        clienteAtivo, cidadeSaoLuis, cidadeBelem,
                        funcionario, categoriaNormal, itens));

        assertTrue(ex.getMessage().toLowerCase().contains("distância"), ex.getMessage());
        System.out.println("[T06] Exceção correta: " + ex.getMessage());
    }

    @Test
    @Order(7)
    @DisplayName("T07 - Deve lançar exceção quando frete não tem itens")
    void deveLancarExcecaoSemItens() {
        FreteException ex = assertThrows(FreteException.class, () ->
                freteService.registrarFrete(
                        clienteAtivo, cidadeSaoLuis, cidadeFortaleza,
                        funcionario, categoriaNormal, Collections.emptyList()));

        assertTrue(ex.getMessage().toLowerCase().contains("item"), ex.getMessage());
        System.out.println("[T07] Exceção correta: " + ex.getMessage());
    }

    @Test
    @Order(8)
    @DisplayName("T08 - Deve lançar exceção quando peso excede capacidade de todos os veículos")
    void deveLancarExcecaoVeiculoIncompativel() {
        // Peso absurdo que nenhum veículo suporta
        List<ItemFrete> itens = criarItens("Carga impossível", 999_999f);

        FreteException ex = assertThrows(FreteException.class, () ->
                freteService.registrarFrete(
                        clienteAtivo, cidadeSaoLuis, cidadeFortaleza,
                        funcionario, categoriaNormal, itens));

        assertTrue(ex.getMessage().toLowerCase().contains("veículo")
                || ex.getMessage().toLowerCase().contains("peso"), ex.getMessage());
        System.out.println("[T08] Exceção correta: " + ex.getMessage());
    }

    // TESTE 2 - CALCULO DO FRETE

    @Test
    @Order(9)
    @DisplayName("T09 - Cálculo correto: peso até 100kg, sem categoria")
    void deveCalcularFreteAte100kg() {
        Frete frete = montarFreteSemPersistir(50f, null);

        BigDecimal valor = freteService.calcularValorFrete(frete, distanciaSlFor);

        // ValorEsperado = (quilometros × 2.00) + adicional, sem acréscimos de peso ou categoria
        int km = distanciaSlFor.getQuilometros();
        BigDecimal ad = distanciaSlFor.getAdicionalKmRodado() != null
                ? distanciaSlFor.getAdicionalKmRodado() : BigDecimal.ZERO;
        BigDecimal esperado = new BigDecimal("2.00")
                .multiply(new BigDecimal(km))
                .add(ad)
                .setScale(2, java.math.RoundingMode.HALF_UP);

        assertEquals(0, esperado.compareTo(valor),
                "Valor calculado deveria ser R$ " + esperado + " mas foi R$ " + valor);
        System.out.println("[T09] Valor calculado (50kg, sem categoria): R$ " + valor);
    }

    @Test
    @Order(10)
    @DisplayName("T10 - Cálculo correto: peso entre 100–300kg adiciona 10%")
    void deveCalcularFreteEntre100e300kg() {
        Frete frete = montarFreteSemPersistir(150f, null);

        BigDecimal valor = freteService.calcularValorFrete(frete, distanciaSlFor);

        int km = distanciaSlFor.getQuilometros();
        BigDecimal ad = distanciaSlFor.getAdicionalKmRodado() != null
                ? distanciaSlFor.getAdicionalKmRodado() : BigDecimal.ZERO;
        BigDecimal base = new BigDecimal("2.00").multiply(new BigDecimal(km)).add(ad);
        BigDecimal esperado = base.multiply(new BigDecimal("1.10"))
                .setScale(2, java.math.RoundingMode.HALF_UP);

        assertEquals(0, esperado.compareTo(valor),
                "Valor calculado deveria ser R$ " + esperado + " mas foi R$ " + valor);
        System.out.println("[T10] Valor calculado (150kg, sem categoria): R$ " + valor);
    }

    @Test
    @Order(11)
    @DisplayName("T11 - Cálculo correto: peso acima de 300kg adiciona 20%")
    void deveCalcularFreteAcima300kg() {
        Frete frete = montarFreteSemPersistir(500f, null);

        BigDecimal valor = freteService.calcularValorFrete(frete, distanciaSlFor);

        int km = distanciaSlFor.getQuilometros();
        BigDecimal ad = distanciaSlFor.getAdicionalKmRodado() != null
                ? distanciaSlFor.getAdicionalKmRodado() : BigDecimal.ZERO;
        BigDecimal base = new BigDecimal("2.00").multiply(new BigDecimal(km)).add(ad);
        BigDecimal esperado = base.multiply(new BigDecimal("1.20"))
                .setScale(2, java.math.RoundingMode.HALF_UP);

        assertEquals(0, esperado.compareTo(valor),
                "Valor calculado deveria ser R$ " + esperado + " mas foi R$ " + valor);
        System.out.println("[T11] Valor calculado (500kg, sem categoria): R$ " + valor);
    }

    @Test
    @Order(12)
    @DisplayName("T12 - Cálculo correto: peso até 100kg com categoria rápida (10%)")
    void deveCalcularFreteComCategoriaRapida() {
        Frete frete = montarFreteSemPersistir(50f, categoriaRapida);

        BigDecimal valor = freteService.calcularValorFrete(frete, distanciaSlFor);

        int km = distanciaSlFor.getQuilometros();
        BigDecimal ad = distanciaSlFor.getAdicionalKmRodado() != null
                ? distanciaSlFor.getAdicionalKmRodado() : BigDecimal.ZERO;
        BigDecimal base = new BigDecimal("2.00").multiply(new BigDecimal(km)).add(ad);
        BigDecimal percCat = BigDecimal.valueOf(categoriaRapida.getPercentualAdicional())
                .divide(BigDecimal.valueOf(100), 4, java.math.RoundingMode.HALF_UP);
        BigDecimal esperado = base.multiply(BigDecimal.ONE.add(percCat))
                .setScale(2, java.math.RoundingMode.HALF_UP);

        assertEquals(0, esperado.compareTo(valor),
                "Valor calculado deveria ser R$ " + esperado + " mas foi R$ " + valor);
        System.out.println("[T12] Valor calculado (50kg + cat. rápida): R$ " + valor);
    }

    @Test
    @Order(13)
    @DisplayName("T13 - Deve lançar exceção quando peso excede capacidade do veículo no cálculo")
    void deveLancarExcecaoPesoExcedeVeiculoNoCalculo() {
        // monta um frete com veículo pequeno mas peso enorme
        Frete frete = montarFreteSemPersistir(99_999f, null);
        frete.setVeiculo(veiculoPequeno); // força veículo pequeno

        FreteException ex = assertThrows(FreteException.class, () ->
                freteService.calcularValorFrete(frete, distanciaSlFor));

        assertTrue(ex.getMessage().toLowerCase().contains("peso")
                || ex.getMessage().toLowerCase().contains("capacidade"), ex.getMessage());
        System.out.println("[T13] Exceção correta: " + ex.getMessage());
    }

    // ATUALIZAÇÃO DE STATUS

    @Test
    @Order(14)
    @DisplayName("T14 - Deve avançar status de PENDENTE para EM_TRANSPORTE")
    void deveAvancarStatusParaEmTransporte() {
        Frete frete = freteService.registrarFrete(
                clienteAtivo, cidadeSaoLuis, cidadeFortaleza,
                funcionario, categoriaNormal, criarItens("Item", 20f));

        assertDoesNotThrow(() ->
                freteService.atualizarStatus(frete.getId(), StatusFrete.EM_TRANSPORTE));

        Frete atualizado = em.find(Frete.class, frete.getId());
        assertEquals(StatusFrete.EM_TRANSPORTE, atualizado.getStatusFrete());
        System.out.println("[T14] Status atualizado para EM_TRANSPORTE. ID: " + frete.getId());
    }

    @Test
    @Order(15)
    @DisplayName("T15 - Deve avançar status de EM_TRANSPORTE para ENTREGUE")
    void deveAvancarStatusParaEntregue() {
        Frete frete = freteService.registrarFrete(
                clienteAtivo, cidadeSaoLuis, cidadeFortaleza,
                funcionario, categoriaNormal, criarItens("Item", 20f));

        freteService.atualizarStatus(frete.getId(), StatusFrete.EM_TRANSPORTE);
        assertDoesNotThrow(() ->
                freteService.atualizarStatus(frete.getId(), StatusFrete.ENTREGUE));

        Frete atualizado = em.find(Frete.class, frete.getId());
        assertEquals(StatusFrete.ENTREGUE, atualizado.getStatusFrete());
        System.out.println("[T15] Status atualizado para ENTREGUE. ID: " + frete.getId());
    }

    @Test
    @Order(16)
    @DisplayName("T16 - Deve lançar exceção ao tentar retroceder status")
    void deveLancarExcecaoRetrocessoDeStatus() {
        Frete frete = freteService.registrarFrete(
                clienteAtivo, cidadeSaoLuis, cidadeFortaleza,
                funcionario, categoriaNormal, criarItens("Item", 20f));

        freteService.atualizarStatus(frete.getId(), StatusFrete.EM_TRANSPORTE);

        FreteException ex = assertThrows(FreteException.class, () ->
                freteService.atualizarStatus(frete.getId(), StatusFrete.PENDENTE));

        assertTrue(ex.getMessage().toLowerCase().contains("retrocesso")
                || ex.getMessage().toLowerCase().contains("não permitido"), ex.getMessage());
        System.out.println("[T16] Exceção correta: " + ex.getMessage());
    }

    @Test
    @Order(17)
    @DisplayName("T17 - Deve lançar exceção ao alterar frete CANCELADO")
    void deveLancarExcecaoAlterarFreteCancelado() {
        Frete frete = freteService.registrarFrete(
                clienteAtivo, cidadeSaoLuis, cidadeFortaleza,
                funcionario, categoriaNormal, criarItens("Item", 20f));

        freteService.atualizarStatus(frete.getId(), StatusFrete.CANCELADO);

        FreteException ex = assertThrows(FreteException.class, () ->
                freteService.atualizarStatus(frete.getId(), StatusFrete.EM_TRANSPORTE));

        assertTrue(ex.getMessage().toLowerCase().contains("cancelado"), ex.getMessage());
        System.out.println("[T17] Exceção correta: " + ex.getMessage());
    }

    @Test
    @Order(18)
    @DisplayName("T18 - Deve lançar exceção ao tentar EM_TRANSPORTE sem estar PENDENTE")
    void deveLancarExcecaoEmTransporteSemSerPendente() {
        Frete frete = freteService.registrarFrete(
                clienteAtivo, cidadeSaoLuis, cidadeFortaleza,
                funcionario, categoriaNormal, criarItens("Item", 20f));

        freteService.atualizarStatus(frete.getId(), StatusFrete.EM_TRANSPORTE);
        freteService.atualizarStatus(frete.getId(), StatusFrete.ENTREGUE);

        // Tentar voltar para EM_TRANSPORTE a partir de ENTREGUE
        FreteException ex = assertThrows(FreteException.class, () ->
                freteService.atualizarStatus(frete.getId(), StatusFrete.EM_TRANSPORTE));

        assertNotNull(ex.getMessage());
        System.out.println("[T18] Exceção correta: " + ex.getMessage());
    }

    // HELPERS

    // Cria uma lista com um único ItemFrete com o peso informado.

    private List<ItemFrete> criarItens(String descricao, float peso) {
        ItemFrete item = new ItemFrete();
        item.setDescricao(descricao);
        item.setPeso(peso);
        return Arrays.asList(item);
    }

    // Monta um objeto Frete em memória (sem persistir) com o peso e categoria informados.
    // Usa o veículo grande por padrão para não travar nos testes de cálculo.

    private Frete montarFreteSemPersistir(float peso, CategoriaFrete categoria) {
        ItemFrete item = new ItemFrete();
        item.setDescricao("Item de teste");
        item.setPeso(peso);

        Frete frete = new Frete(clienteAtivo, veiculoGrande, cidadeSaoLuis, cidadeFortaleza, funcionario);
        frete.setStatusFrete(StatusFrete.PENDENTE);
        frete.setCategoriaFrete(categoria);
        frete.setValorKmRodado(new BigDecimal("2.00"));
        frete.adicionarItem(item);

        return frete;
    }
}
