package Lab09.Atividade02;

public class Produto implements Comparable<Produto>{
    
    private static int proximoId = 1;
    private int id;
    private String nome;
    private double preco;

    public Produto(String nome, double preco){
        this.id = proximoId++;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }
 
    public String getNome() {
        return nome;
    }
 
    public double getPreco() {
        return preco;
    }

    @Override
    public int compareTo(Produto outro){
        int cmp = Double.compare(this.preco, outro.preco);
        if(cmp != 0) return cmp;
        return Integer.compare(this.id, outro.id);
    }

    @Override
    public String toString(){
        return String.format("[ID: %d] %-20s R$ %.2f", id, nome, preco);
    }
}
