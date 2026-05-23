package br.com.locadoraImoveis.teste;

import br.com.locadoraImoveis.dao.AluguelDAO;
import br.com.locadoraImoveis.dao.ClienteDAO;
import br.com.locadoraImoveis.dao.ImovelDAO;
import br.com.locadoraImoveis.dao.LocacaoDAO;
import br.com.locadoraImoveis.model.*;
import br.com.locadoraImoveis.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AluguelDAOtest {

    public static void main(String[] args) {

        AluguelDAO aluguelDAO = new AluguelDAO();
        LocacaoDAO locacaoDAO = new LocacaoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        ImovelDAO imovelDAO = new ImovelDAO();

        Cliente proprietario = new Cliente();
        proprietario.setNome("Daniel V. Proprietário");
        proprietario.setCpf("700.800.900-10");
        proprietario.setTelefone1("9844444444");
        proprietario.setEmail("danielv@email.com");
        proprietario.setDtNascimento(LocalDate.of(1983, 9, 6));
        clienteDAO.inserir(proprietario);

        Cliente inquilino = new Cliente();
        inquilino.setNome("Pablo Tição");
        inquilino.setCpf("800.900.100-20");
        inquilino.setTelefone1("9855555555");
        inquilino.setEmail("pabloticinho@email.com");
        inquilino.setDtNascimento(LocalDate.of(2006, 6, 7));
        clienteDAO.inserir(inquilino);

        TipoImovel tipo = new TipoImovel();
        tipo.setDescricao("Loft");
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(tipo);
        em.getTransaction().commit();
        em.close();

        Imovel imovel = new Imovel();
        imovel.setEndereco("Vila Palmeira, 77");
        imovel.setCep("65020-000");
        imovel.setDormitorios(1);
        imovel.setBanheiros(1);
        imovel.setSuites(0);
        imovel.setMetragem(40);
        imovel.setValorAluguelSugerido(new BigDecimal("850.00"));
        imovel.setProprietario(proprietario);
        imovel.setTipoImovel(tipo);
        imovelDAO.inserir(imovel);

        Locacao locacao = new Locacao();
        locacao.setAtivo(true);
        locacao.setDataInicio(LocalDate.of(2024, 1, 1));
        locacao.setDataFim(LocalDate.of(2025, 1, 1));
        locacao.setDiaVencimento(10);
        locacao.setPercMulta(new BigDecimal("2.00"));
        locacao.setValorAluguel(new BigDecimal("850.00"));
        locacao.setImovel(imovel);
        locacao.setInquilino(inquilino);
        locacaoDAO.inserir(locacao);

        // 1. inserir aluguéis
        System.out.println("Inserindo Alugueis");

        Aluguel a1 = new Aluguel();
        a1.setDtVencimento(LocalDate.of(2024, 3, 10));
        a1.setValorPago(new BigDecimal("850.00"));
        a1.setObs("Pago no prazo");
        a1.setLocacao(locacao);
        aluguelDAO.inserir(a1);
        System.out.println("Aluguel inserido: vencimento " + a1.getDtVencimento());

        Aluguel a2 = new Aluguel();
        a2.setDtVencimento(LocalDate.of(2024, 1, 10));
        a2.setValorPago(new BigDecimal("850.00"));
        a2.setObs("Pago com atraso");
        a2.setLocacao(locacao);
        aluguelDAO.inserir(a2);
        System.out.println("Aluguel inserido: vencimento " + a2.getDtVencimento());

        Aluguel a3 = new Aluguel();
        a3.setDtVencimento(LocalDate.of(2024, 6, 10));
        a3.setValorPago(new BigDecimal("870.00"));
        a3.setObs("Inclui multa");
        a3.setLocacao(locacao);
        aluguelDAO.inserir(a3);
        System.out.println("Aluguel inserido: vencimento " + a3.getDtVencimento());

        Aluguel a4 = new Aluguel();
        a4.setDtVencimento(LocalDate.of(2024, 12, 10));
        a4.setValorPago(new BigDecimal("850.00"));
        a4.setObs("Último mês");
        a4.setLocacao(locacao);
        aluguelDAO.inserir(a4);
        System.out.println("Aluguel inserido: vencimento " + a4.getDtVencimento());

        // listar ordenados por vencimento (DESC)
        System.out.println("\nAlugueis da Locacao " + locacao.getId()
                + " (ordem decrescente por vencimento)");
        List<Aluguel> alugueis = aluguelDAO
                .listarPorLocacaoOrdenadoPorVencimento(locacao.getId());

        LocalDate anterior = null;
        boolean ordemCorreta = true;
        for (Aluguel a : alugueis) {
            System.out.println("Vencimento: " + a.getDtVencimento()
                    + " / Valor pago: R$ " + a.getValorPago()
                    + " / Obs: " + a.getObs());
            if (anterior != null && a.getDtVencimento().isAfter(anterior)) {
                ordemCorreta = false;
            }
            anterior = a.getDtVencimento();
        }
        System.out.println("Ordem decrescente verificada: " + (ordemCorreta ? "Ok" : "Erro"));

        // atualizar aluguel
        System.out.println("\nAtualizando Aluguel");
        a2.setValorPago(new BigDecimal("880.00")); // acrescenta multa retroativa
        a2.setObs("Pago com atraso + multa");
        aluguelDAO.atualizar(a2);
        System.out.println("Aluguel de " + a2.getDtVencimento() + " atualizado.");

        // verificar atualização
        System.out.println("\nNova Lista");
        aluguelDAO.listarPorLocacaoOrdenadoPorVencimento(locacao.getId())
                .forEach(a -> System.out.println(
                        "Vencimento: " + a.getDtVencimento()
                                + " / R$ " + a.getValorPago()
                                + " / " + a.getObs()));
    }
}
