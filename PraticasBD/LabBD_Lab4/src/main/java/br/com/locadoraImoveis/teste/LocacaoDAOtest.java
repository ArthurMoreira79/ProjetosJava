package br.com.locadoraImoveis.teste;

import br.com.locadoraImoveis.dao.ClienteDAO;
import br.com.locadoraImoveis.dao.ImovelDAO;
import br.com.locadoraImoveis.dao.LocacaoDAO;
import br.com.locadoraImoveis.model.*;
import br.com.locadoraImoveis.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LocacaoDAOtest {
    public static void main (String[] args) {
        LocacaoDAO locacaoDAO = new LocacaoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        ImovelDAO imovelDAO = new ImovelDAO();

        Cliente proprietario = new Cliente();
        proprietario.setNome("Vitoria Proprietária");
        proprietario.setCpf("100.200.300-40");
        proprietario.setTelefone1("9811111111");
        proprietario.setEmail("maria.prop@email.com");
        proprietario.setDtNascimento(LocalDate.of(1960, 1, 5));
        clienteDAO.inserir(proprietario);

        Cliente inquilino1 = new Cliente();
        inquilino1.setNome("Filipe Inquilino");
        inquilino1.setCpf("200.300.400-50");
        inquilino1.setTelefone1("9822222222");
        inquilino1.setEmail("joao@email.com");
        inquilino1.setDtNascimento(LocalDate.of(1995, 7, 20));
        clienteDAO.inserir(inquilino1);

        Cliente inquilino2 = new Cliente();
        inquilino2.setNome("Pedro Inquilino");
        inquilino2.setCpf("300.400.500-60");
        inquilino2.setTelefone1("9833333333");
        inquilino2.setEmail("marta@email.com");
        inquilino2.setDtNascimento(LocalDate.of(1992, 3, 15));
        clienteDAO.inserir(inquilino2);

        TipoImovel tipo = new TipoImovel();
        tipo.setDescricao("Kitnet");
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(tipo);
        em.getTransaction().commit();
        em.close();

        Imovel imovel1 = new Imovel();
        imovel1.setEndereco("Rua A, 22");
        imovel1.setCep("65010-000");
        imovel1.setDormitorios(1);
        imovel1.setBanheiros(1);
        imovel1.setSuites(0);
        imovel1.setMetragem(30);
        imovel1.setValorAluguelSugerido(new BigDecimal("700.00"));
        imovel1.setProprietario(proprietario);
        imovel1.setTipoImovel(tipo);
        imovelDAO.inserir(imovel1);

        Imovel imovel2 = new Imovel();
        imovel2.setEndereco("Rua B, 42");
        imovel2.setCep("65010-001");
        imovel2.setDormitorios(2);
        imovel2.setBanheiros(1);
        imovel2.setSuites(0);
        imovel2.setMetragem(50);
        imovel2.setValorAluguelSugerido(new BigDecimal("1000.00"));
        imovel2.setProprietario(proprietario);
        imovel2.setTipoImovel(tipo);
        imovelDAO.inserir(imovel2);

        // inserindo locacoes
        System.out.println("Inserindo Locacoes:");

        Locacao loc1 = new Locacao();
        loc1.setAtivo(true);
        loc1.setDataInicio(LocalDate.of(2024, 1, 1));
        loc1.setDataFim(LocalDate.of(2025, 1, 1));
        loc1.setDiaVencimento(10);
        loc1.setPercMulta(new BigDecimal("2.00"));
        loc1.setValorAluguel(new BigDecimal("700.00"));
        loc1.setImovel(imovel1);
        loc1.setInquilino(inquilino1);
        locacaoDAO.inserir(loc1);
        System.out.println("Locação 1 inserida - Inquilino: " + inquilino1.getNome());

        Locacao loc2 = new Locacao();
        loc2.setAtivo(false); // encerrada
        loc2.setDataInicio(LocalDate.of(2023, 6, 1));
        loc2.setDataFim(LocalDate.of(2024, 6, 1));
        loc2.setDiaVencimento(5);
        loc2.setPercMulta(new BigDecimal("3.00"));
        loc2.setValorAluguel(new BigDecimal("1000.00"));
        loc2.setImovel(imovel2);
        loc2.setInquilino(inquilino1);
        locacaoDAO.inserir(loc2);
        System.out.println("Locação 2 inserida - Inquilino: " + inquilino1.getNome());

        Locacao loc3 = new Locacao();
        loc3.setAtivo(true);
        loc3.setDataInicio(LocalDate.of(2025, 3, 1));
        loc3.setDataFim(LocalDate.of(2026, 3, 1));
        loc3.setDiaVencimento(15);
        loc3.setPercMulta(new BigDecimal("2.50"));
        loc3.setValorAluguel(new BigDecimal("800.00"));
        loc3.setImovel(imovel2);
        loc3.setInquilino(inquilino2);
        locacaoDAO.inserir(loc3);
        System.out.println("Locação 3 inserida - Inquilino: " + inquilino2.getNome());

        // listar locações ativas
        System.out.println("\nLocacoes Ativas");
        List<Locacao> ativas = locacaoDAO.listarAtivas();
        ativas.forEach(l -> System.out.println(
                "ID " + l.getId()
                        + " / Inquilino: " + l.getInquilino().getNome()
                        + " / Valor: R$ " + l.getValorAluguel()
                        + " / Ativo: " + l.getAtivo()));

        // listar por inquilino
        System.out.println("\nLocacoes do INquilino: " + inquilino1.getNome());
        List<Locacao> doInquilino = locacaoDAO.listarPorInquilino(inquilino1.getId());
        doInquilino.forEach(l -> System.out.println(
                "ID " + l.getId()
                        + " / Imóvel: " + l.getImovel().getEndereco()
                        + " / Ativo: " + l.getAtivo()));

        // atualizar locação (encerrar uma ativa)
        System.out.println("\nAtualizando locacao");
        loc1.setAtivo(false);
        loc1.setDataFim(LocalDate.now());
        locacaoDAO.atualizar(loc1);
        System.out.println("Locação ID " + loc1.getId() + " encerrada.");

        System.out.println("\nLocacoes Ativas (atualizado):");
        locacaoDAO.listarAtivas().forEach(l ->
                System.out.println("ID " + l.getId() + " / Inquilino: " + l.getInquilino().getNome()));
    }
}