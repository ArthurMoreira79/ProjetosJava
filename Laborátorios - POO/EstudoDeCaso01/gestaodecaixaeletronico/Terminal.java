package EstudoDeCaso01.gestaodecaixaeletronico;

import java.util.Scanner;

public class Terminal {
    
    private Caixa meuCaixa;
    private int modoAtual; // 0 = supervisor, 1 = cliente

    public Terminal(BdContas bd){
        this.meuCaixa = new Caixa(this, bd);
        this.modoAtual = 0; // começa no modo supervisor
    }

    public void iniciaOperacao(){
        System.out.println("============================================");
        System.out.println("   BEM-VINDO AO TERMINAL DE CAIXA ELETRONICO");
        System.out.println("============================================");

        int opcao = this.getOpcao();
        while(opcao != 9){
            if(this.modoAtual == 0){ //supervisor
                if(opcao == 3){
                    this.meuCaixa.recarrega();
                    System.out.println("Caixa recarregado! Modo cliente ativado.");
                }
            } else{ // cliente
                switch(opcao){
                    case 1:
                        realizarConsultaSaldo();
                        break;
                    case 2:
                        realizarSaque();
                        break;
                    case 4:
                        realizarDeposito();
                        break;
                    case 5:
                        realizarDepositoCheque();
                        break;
                    case 6:
                        realizarPix();
                        break;
                    case 7:
                        realizarTED();
                        break;
                    case 8:
                        realizarExtrato();
                        break;
                }
            }
            opcao = this.getOpcao();
        }
        System.out.println("Obrigado por usar nosso caixa. Ate logo!");
    }

    public void setModo(int modo){
        if(modo == 0 || modo == 1){
            this.modoAtual = modo;
            if(modo == 0){
                System.out.println("\n[AVISO] Saldo do caixa abaixo de R$500,00.");
                System.out.println("[AVISO] Modo supervisor ativado. Nova recarga necessaria.\n");
            }
        }
    }

    // --- Operações ---

    private void realizarConsultaSaldo(){ //Opcao 1
        int conta = getInt("Numero da Conta");
        int senha = getInt("Senha");
        double saldo = this.meuCaixa.consultaSaldo(conta, senha);
        if(saldo >= 0){
            System.out.printf("Seu saldo eh: R$ %.2f%n", saldo);
        } else{
            System.out.println("Conta ou senha invaldia");
        }
    }

    private void realizarSaque(){ //Opcao 2
        int conta = getInt("Numero da Conta");
        int senha = getInt("Senha");
        double valor = getDouble("Valor do saque (multiplo de R$50, maximo R$500)");
        boolean ok = this.meuCaixa.efetuaSaque(conta, valor, senha);
        System.out.println(ok ? "Retire seu dinheiro." : "Pedido de saque recusado.");
    }

    private void realizarDeposito(){ //Opcao 4
        int conta = getInt("Numero da Conta");
        double valor = getDouble("Valor do deposito (dinheiro)");
        boolean ok = this.meuCaixa.efetuaDeposito(conta, valor);
        System.out.println(ok ? "Deposito realizado com sucesso." : "Deposito recusado.");
    }

    private void realizarDepositoCheque(){ //Opcao 5
        int conta = getInt("Numero da Conta");
        double valor = getDouble("Valor do deposito (cheque)");
        boolean ok = this.meuCaixa.efetuaDepositoCheque(conta, valor);
        System.out.println(ok ? "Deposito em cheque realizado." : "Deposito recusado.");
    }

    private void realizarPix() { //Opcao 6
        int contaOrigem = getInt("Sua Conta (origem)");
        int senha = getInt("Senha");
        double valor = getDouble("Valor do PIX");
        int contaDestino = getInt("Conta de destino");
        boolean ok = this.meuCaixa.efetuaPix(contaOrigem, senha, valor, contaDestino);
        System.out.println(ok ? "PIX realizado com sucesso." : "PIX recusado.");
    }

    private void realizarTED() { //Opcao 7
        int contaOrigem = getInt("Sua Conta (origem)");
        int senha = getInt("Senha");
        double valor = getDouble("Valor da TED");
        int contaDestino = getInt("Conta de destino");
        boolean ok = this.meuCaixa.efetuaTED(contaOrigem, senha, valor, contaDestino);
        System.out.println(ok ? "TED realizada com sucesso." : "TED recusada.");
    }

    private void realizarExtrato() { //Opcao 8
        int conta = getInt("Numero da Conta");
        int senha = getInt("Senha");
        String extrato = this.meuCaixa.consultaExtrato(conta, senha);
        if (extrato != null) {
            System.out.println(extrato);
        } else {
            System.out.println("Conta ou senha invalida.");
        }
    }

    // --- Menu ---

    private int getOpcao() {
        int opcao;
        do {
            System.out.println("\n--------------------------------------------");
            if (this.modoAtual == 0) {
                System.out.println("MODO SUPERVISOR");
                System.out.println("[3] Recarregar Caixa");
                System.out.println("[9] Sair");
            } else {
                System.out.println("MODO CLIENTE");
                System.out.println("[1] Consultar Saldo");
                System.out.println("[2] Saque");
                System.out.println("[4] Deposito em Dinheiro");
                System.out.println("[5] Deposito em Cheque");
                System.out.println("[6] PIX");
                System.out.println("[7] TED");
                System.out.println("[8] Extrato");
                System.out.println("[9] Sair");
            }
            System.out.println("--------------------------------------------");

            opcao = getInt("Opcao");
            boolean valida = (modoAtual == 0)
                    ? (opcao == 3 || opcao == 9)
                    : (opcao >= 1 && opcao <= 9 && opcao != 3);
            if (!valida) {
                System.out.println("Opcao invalida. Tente novamente.");
                opcao = 0;
            }
        } while(opcao == 0);
        return opcao;
    }

    private int getInt(String informacao) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite " + informacao + ": ");
        while (!sc.hasNextInt()) {
            System.err.println("Entrada invalida. Apenas numeros inteiros.");
            System.out.print("Digite " + informacao + ": ");
            sc.nextLine();
        }
        return sc.nextInt();
    }

    private double getDouble(String informacao) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite " + informacao + ": ");
        while (!sc.hasNextDouble()) {
            System.err.println("Entrada invalida. Digite um valor numerico.");
            System.out.print("Digite " + informacao + ": ");
            sc.nextLine();
        }
        return sc.nextDouble();
    }
}
