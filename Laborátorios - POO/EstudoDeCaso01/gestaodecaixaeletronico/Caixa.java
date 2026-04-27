package EstudoDeCaso01.gestaodecaixaeletronico;

import EstudoDeCaso01.gestaodecontas.Conta;

public class Caixa {
    
    private Terminal meuTerminal;
    private BdContas bdContas;
    private double saldo; //saldo de cédulas do caixa físico

    public Caixa(Terminal terminal, BdContas bd){
        this.meuTerminal = terminal;
        this.bdContas = bd;
    }

    public double consultaSaldo(int numeroDaConta, int senha){ //Consulta saldo da conta bancária
        Conta conta = this.bdContas.buscaConta(numeroDaConta);
        if(conta != null){
            return conta.verificaSaldo(senha);
        }
        return -1.0;
    }

    public boolean efetuaSaque(int numeroDaConta, double valor, int senha){ //saque multiplo de 50, max:500, saldo suficiente
        if(valor <= 0 || (valor % 50) != 0 || valor > 500 || valor > this.saldo){
            return false;
        }
        Conta conta = bdContas.buscaConta(numeroDaConta);
        if(conta == null || !conta.debitaValor(valor, senha, "Saque Automatico")){
            return false;
        }
        this.liberaCedulas((int) (valor / 50));
        this.saldo -= valor;
        if(this.saldo < 500){
            this.meuTerminal.setModo(0); //volta ao modo de supervisor
        }
        return true;
    }

    public boolean efetuaDeposito(int numeroDaConta, double valor){ //Deposito em dinheiro
        if(valor <= 0) return false;
        Conta conta = bdContas.buscaConta(numeroDaConta);
        if(conta == null) return false;
        return conta.creditaValor(valor, "Deposito em Dinheiro");
    }

    public boolean efetuaDepositoCheque(int numeroDaConta, double valor){ //Deposito em Cheque
        if(valor <= 0) return false;
        Conta conta = bdContas.buscaConta(numeroDaConta);
        if(conta == null) return false;
        return conta.creditaValor(valor, "Deposito em Cheque");
    }

    public boolean efetuaPix(int contaOrigem, int senha, double valor, int contaDestino){ //PIX(débito na origem, crédito no destino)
        if (valor <= 0) return false;
        Conta origem = bdContas.buscaConta(contaOrigem);
        Conta destino = bdContas.buscaConta(contaDestino);
        if (origem == null || destino == null) return false;
        if (!origem.debitaValor(valor, senha, "PIX Enviado")) return false;
        destino.creditaValor(valor, "PIX Recebido");
        return true;
    }

    public boolean efetuaTED(int contaOrigem, int senha, double valor, int contaDestino){ //TED(débito na origem, crédito no destino)
        if (valor <= 0) return false;
        Conta origem = bdContas.buscaConta(contaOrigem);
        Conta destino = bdContas.buscaConta(contaDestino);
        if (origem == null || destino == null) return false;
        if (!origem.debitaValor(valor, senha, "TED Enviada")) return false;
        destino.creditaValor(valor, "TED Recebida");
        return true;
    }

    public String consultaExtrato(int numeroDaConta, int senha){ //Extrato Bancario
        Conta conta = bdContas.buscaConta(numeroDaConta);
        if(conta == null) return null;
        return conta.getExtrato(senha);
    }

    public void recarrega(){ //Recarga das cédulas do caixa físico
        this.saldo = 2000;
        this.meuTerminal.setModo(1); // volta pro modo cliente
    }

    private void liberaCedulas(int quantidade){
        System.out.println(">>> Dispensando cedulas:");
        for(int i = 0; i < quantidade; i++){
            System.out.println("    ===/ R$50,00 /===");
        }
    }
}
