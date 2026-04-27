package EstudoDeCaso02.gestaodeleilao;

import java.util.ArrayList;
import java.util.Scanner;

import EstudoDeCaso02.gestaodelotes.Lote;

public class Interface {
    
    private Leilao leilao;
    private int modoAtual; // 1 = cadastro, 2 = leilão
    private Scanner scanner;
 
    public Interface(Leilao leilao) {
        this.leilao = leilao;
        this.modoAtual = 1;
        this.scanner = new Scanner(System.in);
    }
 
    public void iniciaOperacao() {
        System.out.println("=================================================");
        System.out.println("   BEM-VINDO AO SISTEMA DE LEILAO VIRTUAL");
        System.out.println("=================================================");
 
        boolean rodando = true;
        while (rodando) {
            exibeMenu();
            int opcao = getOpcao();
 
            if (modoAtual == 1) {
                // Modo Cadastro
                switch (opcao) {
                    case 1:
                        adicionarLote();
                        break;
                    case 2:
                        removerLote();
                        break;
                    case 3:
                        System.out.println("\n--- TODOS OS LOTES ---");
                        System.out.println(leilao.obtemTodosOsLotes());
                        break;
                    case 4:
                        mostrarLote();
                        break;
                    case 5:
                        iniciarLeilao();
                        break;
                    case 9:
                        System.out.println("Encerrando o sistema. Ate logo!");
                        rodando = false;
                        break;
                    default:
                        System.out.println("Opcao invalida. Tente novamente.");
                }
            } else {
                // Modo Leilão
                if (leilao.isAtivo()) {
                    switch (opcao) {
                        case 3:
                            System.out.println("\n--- TODOS OS LOTES ---");
                            System.out.println(leilao.obtemTodosOsLotes());
                            break;
                        case 4:
                            mostrarLote();
                            break;
                        case 6:
                            ofertarLance();
                            break;
                        case 7:
                            System.out.println(leilao.encerraLeilao());
                            break;
                        default:
                            System.out.println("Opcao invalida. Tente novamente.");
                    }
                } else {
                    // Leilão inativo (encerrado)
                    switch (opcao) {
                        case 3:
                            System.out.println("\n--- TODOS OS LOTES ---");
                            System.out.println(leilao.obtemTodosOsLotes());
                            break;
                        case 4:
                            mostrarLote();
                            break;
                        case 8:
                            mostrarLotesNaoVendidos();
                            break;
                        case 9:
                            System.out.println("Encerrando o sistema. Ate logo!");
                            rodando = false;
                            break;
                        default:
                            System.out.println("Opcao invalida. Tente novamente.");
                    }
                }
            }
        }
        scanner.close();
    }
 
    private void exibeMenu() {
        System.out.println("\n-------------------------------------------------");
        if (modoAtual == 1) {
            System.out.println("MODO: CADASTRO");
            System.out.println("[1] Adicionar Lote");
            System.out.println("[2] Remover Lote");
            System.out.println("[3] Mostrar todos os Lotes");
            System.out.println("[4] Mostrar Lote");
            System.out.println("[5] Iniciar Leilao");
            System.out.println("[9] Sair do Programa");
        } else if (leilao.isAtivo()) {
            System.out.println("MODO: LEILAO ATIVO");
            System.out.println("[3] Mostrar todos os Lotes");
            System.out.println("[4] Mostrar Lote");
            System.out.println("[6] Ofertar Lance");
            System.out.println("[7] Encerrar Leilao");
        } else {
            System.out.println("MODO: LEILAO ENCERRADO");
            System.out.println("[3] Mostrar todos os Lotes");
            System.out.println("[4] Mostrar Lote");
            System.out.println("[8] Mostrar Lotes nao Vendidos");
            System.out.println("[9] Sair do Programa");
        }
        System.out.println("-------------------------------------------------");
    }
 
    private int getOpcao() {
        System.out.print("Escolha uma opcao: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
 
    private double getNumero(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite um numero.");
            }
        }
    }
 
    private String getString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }
 
    private void adicionarLote() {
        String descricao = getString("Descricao do lote: ");
        double valorMinimo = getNumero("Valor minimo (R$): ");
        leilao.adicionaLote(descricao, valorMinimo);
        System.out.println("Lote adicionado com sucesso!");
    }
 
    private void removerLote() {
        int numero = (int) getNumero("Numero do lote a remover: ");
        Lote removido = leilao.removeLote(numero);
        if (removido != null) {
            System.out.println("Lote removido: " + removido.getDescricao());
        } else {
            System.out.println("Lote nao encontrado.");
        }
    }
 
    private void mostrarLote() {
        int numero = (int) getNumero("Numero do lote: ");
        Lote lote = leilao.getLote(numero);
        if (lote != null) {
            System.out.println("\n" + lote.toString());
        } else {
            System.out.println("Lote nao encontrado.");
        }
    }
 
    private void iniciarLeilao() {
        if (leilao.quantidadeDeLotes() == 0) {
            System.out.println("Cadastre ao menos um lote antes de iniciar o leilao.");
            return;
        }
        leilao.setAtivo(true);
        modoAtual = 2;
        System.out.println("Leilao iniciado! Bom leilao!");
    }
 
    private void ofertarLance() {
        int numeroLote = (int) getNumero("Numero do lote: ");
        String nome = getString("Seu nome: ");
        double valor = getNumero("Valor do lance (R$): ");
        System.out.println(leilao.ofertaLance(numeroLote, nome, valor));
    }
 
    private void mostrarLotesNaoVendidos() {
        ArrayList<Lote> naoVendidos = leilao.getNaoVendidos();
        System.out.println("\n--- LOTES NAO VENDIDOS ---");
        if (naoVendidos.isEmpty()) {
            System.out.println("Todos os lotes foram vendidos!");
        } else {
            for (Lote lote : naoVendidos) {
                System.out.println(lote.toString());
            }
        }
    }
}
