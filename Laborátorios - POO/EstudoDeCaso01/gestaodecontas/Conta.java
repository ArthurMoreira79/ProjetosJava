package EstudoDeCaso01.gestaodecontas;

public class Conta {
    
    private int numero;
    private int senha;
    private Cliente titular;
    private double saldo;
    private HistoricoDeLancamentos historico;
 
    public Conta(int numero, Cliente titular, int senha, double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.senha = senha;
        this.saldo = saldo;
        this.historico = new HistoricoDeLancamentos(10);
    }
 
    public int getNumero() {
        return this.numero;
    }
 
    public Cliente getTitular() {
        return this.titular;
    }
 
    public void setTitular(Cliente titular) {
        this.titular = titular;
    }
    
    private boolean senhaEhValida(int senha){
        return this.senha == senha;
    }

    public double verificaSaldo(int senha){ //Retorna o saldo se a senha for válida, caso contrário, volta -1
        if(senhaEhValida(senha)){
            return this.saldo;
        }
        return -1;
    }

    public boolean debitaValor(double valor, int senha, String operacaoBancaria){ //Débito Genérico (Saque, TED, PIX)
        if(!senhaEhValida(senha) || valor > this.saldo || valor <= 0){
            return false;
        }
        this.saldo -= valor;
        this.historico.insereLancamento(new Lancamento(operacaoBancaria, -valor));
        return true;
    }

    public boolean creditaValor(double valor, String operacaoBancaria){ //Crédito Denérico (Déposito, TED e PIX recebidos)
        if(valor <= 0){
            return false;
        }
        this.saldo += valor;
        this.historico.insereLancamento(new Lancamento(operacaoBancaria, valor));
        return true;
    }

    public String getExtrato(int senha){ //Retorna extrato + saldo atual
        if(!senhaEhValida(senha)){
            return null;
        }

        StringBuilder extrato = new StringBuilder();
        extrato.append("=== EXTRATO BANCARIO ===\n");
        extrato.append(String.format("Conta: %d | Titular: %s%n", this.numero, this.titular.getNome()));
        extrato.append("------------------------\n");
        String lancamentos = this.historico.geraHistoricoDeLancamentos();

        if(lancamentos.isEmpty()){
            extrato.append("Nenhum lancamento registrado.\n");
        } else{
            extrato.append(lancamentos);
        }
        extrato.append("------------------------\n");
        extrato.append(String.format("Saldo atual: R$ %.2f%n", this.saldo));
        return extrato.toString();
    }

}
