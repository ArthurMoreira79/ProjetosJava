package Lab04;

public class Lancamento {
    
    private String descricao;
    private double valor;
 
    public Lancamento(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }
 
    public String getDescricao() {
        return this.descricao;
    }
 
    public double getValor() {
        return this.valor;
    }
 
    @Override
    public String toString() {
        String tipo = valor >= 0 ? "Crédito" : "Débito";
        return String.format("Lancamento{tipo='%s', descricao='%s', valor=R$ %.2f}",
                tipo, descricao, valor);
    }
}
