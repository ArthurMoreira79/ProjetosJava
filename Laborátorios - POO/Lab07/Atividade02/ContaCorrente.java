package Lab07.Atividade02;

public class ContaCorrente implements Tributavel {
    
    private int numero;
    private String titular;
    private double saldo;

    public ContaCorrente(int numero, String titular, double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }
 
    public int getNumero() {
        return this.numero;
    }
 
    public String getTitular() {
        return this.titular;
    }
 
    public double getSaldo() {
        return this.saldo;
    }

    @Override
    public double calculaTributos(){
        return this.saldo * 0.01;
    }

    @Override
    public String toString(){
        return String.format("ContaCorrente[%d | %s | saldo: R$ %.2f]", this.numero, this.titular, this.saldo);
    }
}
