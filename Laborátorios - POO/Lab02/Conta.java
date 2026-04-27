package Lab02;

public class Conta {

    private int numero;
    private Cartao cartao;
    private int senha;
    private double saldo;

    public Conta(int numConta, int numCartao, Cliente titular, int senha, double saldo){
        this.numero = numConta;
        this.cartao = new Cartao(numCartao, titular);
        this.senha = senha;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }
 
    public Cartao getCartao() {
        return cartao;
    }

    public int getSenha(){
        return senha;
    }
 
    public void alterarCartao(int numeroDoCartao, Cliente titular){
        this.cartao = new Cartao(numeroDoCartao, titular);
    }
 
    public double getSaldo() {
        return saldo;
    }

    public void deposita(double valor){
        if(valor > 0){
            this.saldo += valor;
        }
    }

    public boolean saca(double valor){
        if(valor > 0 && valor <= this.saldo){
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return "Conta [Numero: " + numero + ", Cartao: " + cartao + ", Saldo: " + saldo + "]";
    }
}
