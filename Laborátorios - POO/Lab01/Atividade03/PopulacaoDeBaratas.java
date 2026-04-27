package Lab01.Atividade03;

public class PopulacaoDeBaratas {

    private long populacao;

    public PopulacaoDeBaratas(long populacao){
        this.populacao = populacao;
    }

    public PopulacaoDeBaratas(){
        this.populacao = 5;
    }

    public void esperaUmTempo(){
        this.populacao *= 2;
    }

    public void pulverizar(){
        this.populacao = (long) (this.populacao * 0.75);
    }

    public long getPopulacao(){
        return populacao;
    }

    @Override
    public String toString(){
        return "População de Baratas: " + populacao;
    }
}
