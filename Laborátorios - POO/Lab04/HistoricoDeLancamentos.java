package Lab04;

public class HistoricoDeLancamentos {
    
    private Lancamento[] lancamentos;
    private int ultimoLancamento;

    public HistoricoDeLancamentos(int numeroDeLancamentos){
        this.lancamentos = new Lancamento[numeroDeLancamentos + 1];
        this.ultimoLancamento = 0;
    }

    public void insereLancamento(Lancamento lancamento){
        if(this.ultimoLancamento == this.lancamentos.length - 1){
            for(int i = 0; i < this.lancamentos.length - 1; i++){
                this.lancamentos[i] = this.lancamentos[i + 1];
            }
        } else{
            this.ultimoLancamento++;
        }
        this.lancamentos[this.ultimoLancamento] = lancamento;
    }

    public void exibeExtrato(){
        System.out.println("=== Extrato de Lancamento ===");
        if(ultimoLancamento == 0){
            System.out.println("Nenhum lancamento registrado.");
            return;
        }
        for(int i = 1; i < this.ultimoLancamento; i++){
            System.out.println(this.lancamentos[i]);
        }
        System.out.println("=========================");
    }

    public Lancamento[] getLancamentos(){
        Lancamento[] resultado = new Lancamento[this.ultimoLancamento];
        for(int i = 0; i < this.ultimoLancamento; i++){
            resultado[i] = this.lancamentos[i + 1];
        }
        return resultado;
    }
}
