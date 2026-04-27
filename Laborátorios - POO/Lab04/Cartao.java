package Lab04;

public class Cartao {
    
    private int numero;
    private Cliente titular;
 
    public Cartao(int numero, Cliente titular) {
        this.numero = numero;
        this.titular = titular;
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
 
    @Override
    public String toString() {
        return "[ Numero = " + numero + ", Titular = " + titular + "]";
    }
}
