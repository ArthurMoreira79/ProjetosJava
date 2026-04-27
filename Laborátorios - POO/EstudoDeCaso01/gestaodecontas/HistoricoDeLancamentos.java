package EstudoDeCaso01.gestaodecontas;

public class HistoricoDeLancamentos {
    
    private Lancamento[] lancamentos;
    private int ultimoLancamento;

    public HistoricoDeLancamentos(int numeroDeLancamentos){
        this.lancamentos = new Lancamento[numeroDeLancamentos + 1]; //+1 pra facilitar indexação a partir de 1
        this.ultimoLancamento = 0;
    }

    public void insereLancamento(Lancamento lancamento){ //se o array ta cheio, desloca tudo pra esquerda e descarta o mais antigo
        if(this.ultimoLancamento == this.lancamentos.length - 1){
            for(int i = 1; i < this.lancamentos.length - 1; i++){
                this.lancamentos[i] = this.lancamentos[i + 1];
            } //mantém ultimoLancamento no fim
        } else{
            this.ultimoLancamento++;
        }
        this.lancamentos[this.ultimoLancamento] = lancamento;
    }

    public String geraHistoricoDeLancamentos(){ //registro do extrato com os 10 ultimos lançamentos
        StringBuilder historico = new StringBuilder();
            for(int i = 1; i <= this.ultimoLancamento; i++){
                double valor = this.lancamentos[i].getValor();
                String sinal = valor >= 0 ? "+" : "";
                historico.append(String.format("%-25s %sR$ %.2f%n", this.lancamentos[i].getDescricao(), sinal, valor));
            }
            return historico.toString();
    }
}
