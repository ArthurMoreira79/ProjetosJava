package EstudoDeCaso02.gestaodelotes;

public class Lote {
    
    private int numero;
    private String descricao;
    private double valorMinimo;
    private Lance maiorLance;
 
    public Lote(int numero, String descricao, double valorMinimo) {
        this.numero = numero;
        this.descricao = descricao;
        this.valorMinimo = valorMinimo;
    }
 
    public int getNumero() {
        return this.numero;
    }
 
    public String getDescricao() {
        return this.descricao;
    }
 
    public double getValorMinimo() {
        return this.valorMinimo;
    }
 
    public Lance getMaiorLance() {
        return this.maiorLance;
    }
 
    public boolean lancePara(Pessoa licitante, double valor) {
        if (valor >= this.valorMinimo &&
                (this.maiorLance == null || this.maiorLance.getValor() < valor)) {
            this.maiorLance = new Lance(licitante, valor);
            return true;
        }
        return false;
    }
 
    @Override
    public String toString() {
        StringBuilder lote = new StringBuilder();
        lote.append("Lote numero: ").append(this.numero)
            .append(" | ").append(this.descricao)
            .append(" | Valor minimo: R$ ").append(String.format("%.2f", this.valorMinimo));
 
        if (this.maiorLance != null) {
            lote.append(" | Maior lance: R$ ")
                .append(String.format("%.2f", this.maiorLance.getValor()))
                .append(" (").append(this.maiorLance.getLicitante().getNome()).append(")");
        } else {
            lote.append(" | (Nenhum Lance)");
        }
 
        lote.append("\n");
        return lote.toString();
    }
}
