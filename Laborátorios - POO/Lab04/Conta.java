package Lab04;

public class Conta {

    private int numero;
    private Cartao cartao;
    private Cliente titular;
    private int senha;
    private double saldo;
    private HistoricoDeLancamentos historico;

    public Conta(int numConta, int numCartao, Cliente titular, int senha, double saldo){
        this.numero = numConta;
        this.cartao = new Cartao(numCartao, titular);
        this.titular = titular;
        this.senha = senha;
        this.saldo = saldo;
        this.historico = new HistoricoDeLancamentos(10);
    }

    public int getNumero() {
        return numero;
    }
 
    public Cartao getCartao() {
        return cartao;
    }
    
    public void alteraCartao(int numeroDoCartao, Cliente titular){
        this.cartao = new Cartao(numeroDoCartao, titular);
    }

    public int getSenha(){
        return senha;
    }
 
    public Cliente getTitular(){
        return this.titular;
    }
 
    public double getSaldo() {
        return saldo;
    }

    private boolean senhaEhValida(int senha){
        return this.senha == senha;
    }

    public boolean debitaValor(double valor, int senha, String operacaoBancaria){
        if(!senhaEhValida(senha) || valor > this.saldo || valor < 0){
            return false;
        }
        this.saldo -= valor;
        this.historico.insereLancamento(new Lancamento(operacaoBancaria, -valor));
        return true;
    }

    public boolean creditaValor(double valor, String operacaoBancaria){
        if(valor < 0){
            return false;
        }
        this.saldo += valor;
        this.historico.insereLancamento(new Lancamento(operacaoBancaria, valor));
        return true;
    }

    void exibeExtrato(){
        System.out.println("Conta N: " + this.numero + " | Titular: " + this.titular.getNome());
        System.out.printf("Saldo atual: R$ %.2f%n", this.saldo);
        this.historico.exibeExtrato();
    }

    @Override
    public String toString(){
        return String.format("\nConta{Numero = %d, Saldo = R$ %.2f, Cartao = %s}", numero, saldo, cartao);
    }
}
