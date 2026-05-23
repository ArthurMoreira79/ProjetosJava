package br.com.locadoraImoveis.teste;

import br.com.locadoraImoveis.dao.ClienteDAO;
import br.com.locadoraImoveis.dao.ImovelDAO;
import br.com.locadoraImoveis.model.Cliente;
import br.com.locadoraImoveis.model.Imovel;
import br.com.locadoraImoveis.model.TipoImovel;
import jakarta.persistence.EntityManager;
import br.com.locadoraImoveis.util.JPAUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ImovelDAOtest {

    public static void main(String[] args) {

        ImovelDAO imovelDAO = new ImovelDAO();
        ClienteDAO clienteDAO = new ClienteDAO();

        // criar proprietário e tipo de imóvel
        Cliente proprietario = new Cliente();
        proprietario.setNome("Pedro Proprietário");
        proprietario.setCpf("999.888.777-66");
        proprietario.setTelefone1("9855554444");
        proprietario.setEmail("carlos@email.com");
        proprietario.setDtNascimento(LocalDate.of(1985, 2, 5));
        clienteDAO.inserir(proprietario);

        TipoImovel tipoCasa = new TipoImovel();
        tipoCasa.setDescricao("Casa");
        TipoImovel tipoApto = new TipoImovel();
        tipoApto.setDescricao("Apartamento");

        //entity manager para gerenciar as entidades
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(tipoCasa);
        em.persist(tipoApto);
        em.getTransaction().commit();
        em.close();

        System.out.println("Inserindo imoveis");

        Imovel i1 = new Imovel();
        i1.setEndereco("Tambau, 100");
        i1.setCep("65000-001");
        i1.setDormitorios(3);
        i1.setBanheiros(2);
        i1.setSuites(1);
        i1.setMetragem(90);
        i1.setValorAluguelSugerido(new BigDecimal("1500.00"));
        i1.setProprietario(proprietario);
        i1.setTipoImovel(tipoCasa);
        imovelDAO.inserir(i1);
        System.out.println("Imovel 1 inserido: " + i1.getEndereco());

        Imovel i2 = new Imovel();
        i2.setEndereco("Av. dos Franceses, 500 - Apto 101");
        i2.setCep("65000-001");
        i2.setDormitorios(2);
        i2.setBanheiros(1);
        i2.setSuites(0);
        i2.setMetragem(60);
        i2.setValorAluguelSugerido(new BigDecimal("1000.00"));
        i2.setProprietario(proprietario);
        i2.setTipoImovel(tipoApto);
        imovelDAO.inserir(i2);
        System.out.println("Imovel 2 inserido: " + i2.getEndereco());

        Imovel i3 = new Imovel();
        i3.setEndereco("Angelim, 22");
        i3.setCep("65001-999");
        i3.setDormitorios(4);
        i3.setBanheiros(3);
        i3.setSuites(2);
        i3.setMetragem(150);
        i3.setValorAluguelSugerido(new BigDecimal("2500.00"));
        i3.setProprietario(proprietario);
        i3.setTipoImovel(tipoCasa);
        imovelDAO.inserir(i3);
        System.out.println("Imovel 3 inserido: " + i3.getEndereco());

        // listar imoveis
        System.out.println("\nListar os Imoveis: ");
        List<Imovel> todos = imovelDAO.listarTodos();
        todos.forEach(i -> System.out.println(i.getEndereco()
                + " / R$ " + i.getValorAluguelSugerido()
                + " / CEP: " + i.getCep()));

        // busca por cep
        System.out.println("\nBusca por CEP: 65000-001");
        List<Imovel> porCep = imovelDAO.buscarPorCep("65000-001");
        porCep.forEach(i -> System.out.println(i.getEndereco()));

        // listar por tipo
        System.out.println("\nListar por tipo: Casa (id=" + tipoCasa.getId() + ")");
        List<Imovel> casas = imovelDAO.listarPorTipo(tipoCasa.getId());
        casas.forEach(i -> System.out.println(i.getEndereco()));

        // listar por prop
        System.out.println("\nImoveis por proprietario: " + proprietario.getNome());
        List<Imovel> doProprietario = imovelDAO.listarPorProprietario(proprietario.getId());
        doProprietario.forEach(i -> System.out.println(i.getEndereco()));

        // atualizar imovel
        System.out.println("\nAtualizar imovel");
        i1.setValorAluguelSugerido(new BigDecimal("1700.00"));
        i1.setObs("Reformado recentemente");
        imovelDAO.atualizar(i1);
        System.out.println("Imóvel atualizado. Novo valor: " + i1.getValorAluguelSugerido());
    }

}