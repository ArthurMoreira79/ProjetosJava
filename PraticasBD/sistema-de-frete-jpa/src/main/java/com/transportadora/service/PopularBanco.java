package com.transportadora.service;

import com.transportadora.model.*;
import com.transportadora.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.Date;

 // Popula o banco de dados com dados de teste.
 // Execute esta classe ANTES de rodar os testes do FreteServiceTest.

public class PopularBanco {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();
        try {
            // --- TipoVeiculo ---
            TipoVeiculo tipoFurgao = new TipoVeiculo();
            tipoFurgao.setDescricao("Furgão");
            tipoFurgao.setPesoMaximo(500f);
            em.persist(tipoFurgao);

            TipoVeiculo tipoCaminhao = new TipoVeiculo();
            tipoCaminhao.setDescricao("Caminhão");
            tipoCaminhao.setPesoMaximo(5000f);
            em.persist(tipoCaminhao);

            // --- Funcionário responsável pela Filial ---
            Funcionario gerenteFilial = new Funcionario();
            gerenteFilial.setNome("Carlos Responsável");
            gerenteFilial.setCpf("000.000.000-00");
            gerenteFilial.setEmail("gerente@transportadora.com");
            gerenteFilial.setTelefone("(98) 99999-0000");
            gerenteFilial.setMatricula(1001);
            gerenteFilial.setDataAdmissao(new Date());
            gerenteFilial.setCargo("Gerente de Filial");
            em.persist(gerenteFilial);

            // --- Filial ---
            Filial filialSaoLuis = new Filial();
            filialSaoLuis.setNome("Filial São Luís");
            filialSaoLuis.setEndereco("Av. dos Holandeses, 100 - São Luís, MA");
            filialSaoLuis.setTelefone("(98) 3333-1111");
            filialSaoLuis.setResponsavel(gerenteFilial);
            em.persist(filialSaoLuis);

            // --- Veículos ---
            Veiculo furgao = new Veiculo();
            furgao.setNumeroPlaca("ABC-1234");
            furgao.setPesoMaximo(200);
            furgao.setFilial(filialSaoLuis);
            furgao.setTipoVeiculo(tipoFurgao);
            em.persist(furgao);

            Veiculo caminhao = new Veiculo();
            caminhao.setNumeroPlaca("XYZ-5678");
            caminhao.setPesoMaximo(1000);
            caminhao.setFilial(filialSaoLuis);
            caminhao.setTipoVeiculo(tipoCaminhao);
            em.persist(caminhao);

            // --- Cidades ---
            Cidade saoLuis = new Cidade();
            saoLuis.setNome("São Luís");
            saoLuis.setUf("MA");
            saoLuis.setEstado("Maranhão");
            em.persist(saoLuis);

            Cidade fortaleza = new Cidade();
            fortaleza.setNome("Fortaleza");
            fortaleza.setUf("CE");
            fortaleza.setEstado("Ceará");
            em.persist(fortaleza);

            Cidade belem = new Cidade();
            belem.setNome("Belém");
            belem.setUf("PA");
            belem.setEstado("Pará");
            em.persist(belem);

            // --- Distância (São Luís → Fortaleza, sem distância para Belém) ---
            Distancia distSlFor = new Distancia();
            distSlFor.setOrigem(saoLuis);
            distSlFor.setDestino(fortaleza);
            distSlFor.setQuilometros(800);
            distSlFor.setAdicionalKmRodado(new BigDecimal("50.00"));
            em.persist(distSlFor);

            // --- Categorias de Frete ---
            CategoriaFrete normal = new CategoriaFrete();
            normal.setNome("Normal");
            normal.setDescricao("Entrega padrão sem acréscimo");
            normal.setPercentualAdicional(0f);
            em.persist(normal);

            CategoriaFrete rapida = new CategoriaFrete();
            rapida.setNome("Rápida");
            rapida.setDescricao("Entrega rápida com acréscimo de 10%");
            rapida.setPercentualAdicional(10f);
            em.persist(rapida);

            CategoriaFrete superRapida = new CategoriaFrete();
            superRapida.setNome("Super Rápida");
            superRapida.setDescricao("Entrega super-rápida com acréscimo de 30%");
            superRapida.setPercentualAdicional(30f);
            em.persist(superRapida);

            // --- Funcionário responsável pelos fretes ---
            Funcionario funcResponsavel = new Funcionario();
            funcResponsavel.setNome("Ana Lima");
            funcResponsavel.setCpf("111.222.333-44");
            funcResponsavel.setEmail("ana@transportadora.com");
            funcResponsavel.setTelefone("(98) 98888-1234");
            funcResponsavel.setMatricula(1002);
            funcResponsavel.setDataAdmissao(new Date());
            funcResponsavel.setCargo("Operador de Logística");
            em.persist(funcResponsavel);

            // --- Clientes ---
            Cliente clienteAtivo = new Cliente();
            clienteAtivo.setNome("João Farias");
            clienteAtivo.setCpf("555.666.777-88");
            clienteAtivo.setEmail("joao@email.com");
            clienteAtivo.setTelefone("(98) 97777-5555");
            clienteAtivo.setContato("João");
            clienteAtivo.setAtivo(true);
            em.persist(clienteAtivo);

            Cliente clienteInativo = new Cliente();
            clienteInativo.setNome("Maria Inativa");
            clienteInativo.setCpf("999.888.777-66");
            clienteInativo.setEmail("maria@email.com");
            clienteInativo.setTelefone("(98) 96666-4444");
            clienteInativo.setContato("Maria");
            clienteInativo.setAtivo(false);
            em.persist(clienteInativo);

            em.getTransaction().commit();
            System.out.println("Banco populado com sucesso!");

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao popular banco: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}