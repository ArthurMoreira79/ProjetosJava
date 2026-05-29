package br.com.locadoraImoveis.teste;

import br.com.locadoraImoveis.dao.ClienteDAO;
import br.com.locadoraImoveis.model.Cliente;

import java.time.LocalDate;

public class ClienteDAOtest {

    public static void main(String[] args) {
        ClienteDAO dao = new ClienteDAO();

        System.out.println("Inserindo Clientes");

        Cliente c1 = new Cliente();
        c1.setNome("Arthur Moreira");
        c1.setCpf("111.222.333-44");
        c1.setTelefone1("9899991111");
        c1.setTelefone2("9833332222");
        c1.setEmail("arthurmoreira@email.com");
        c1.setDtNascimento(LocalDate.of(2000, 5, 22));
        dao.inserir(c1);
        System.out.println("Cliente 1 inserido: " + c1.getNome());

        Cliente c2 = new Cliente();
        c2.setNome("Pedro Lucas");
        c2.setCpf("333.444.555.67");
        c2.setTelefone1("9988882222");
        c2.setTelefone2("9844445555");
        c2.setEmail("pedrolucas@email.com");
        c2.setDtNascimento(LocalDate.of(2002, 7, 22));
        dao.inserir(c2);
        System.out.println("Cliente 2 inserido: " + c2.getNome());

        // buscar cpf
        System.out.println("Busca por cpf: ");
        Cliente encontradoCpf = dao.buscarPorCPF("333.444.555.67");
        if (encontradoCpf != null) {
            System.out.println("Encontrado por CPF: " + encontradoCpf.getNome()
                    + " / Email: " + encontradoCpf.getEmail());
        } else {
            System.out.println("Cliente não encontrado pelo CPF informado.");
        }
        // caso de CPF nao existir
        Cliente naoEncontrado = dao.buscarPorCPF("000.000.000-00");
        System.out.println("CPF inexistente retornou: " + naoEncontrado);

        // buscar Email
        System.out.println("\nBusca por email: ");
        Cliente encontradoEmail = dao.buscaPorEmail("arthurmoreira@email.com");
        if (encontradoEmail != null) {
            System.out.println("Encontrado por Email: " + encontradoEmail.getNome()
                    + " / CPF: " + encontradoEmail.getCpf());
        } else {
            System.out.println("Cliente não encontrado pelo e-mail.");
        }

        // atualizar cliente
        System.out.println("\nAtualizando cliente: ");
        if (encontradoCpf != null) {
            encontradoCpf.setTelefone1("9867696769");
            encontradoCpf.setEmail("pedro.novo@email.com");
            dao.atualizar(encontradoCpf);
            System.out.println("Cliente atualizado.");

            // verificacao
            Cliente verificado = dao.buscarPorCPF("333.444.555.67");
            System.out.println("Novo telefone: " + verificado.getTelefone1());
            System.out.println("Novo email: " + verificado.getEmail());
        }
    }

}