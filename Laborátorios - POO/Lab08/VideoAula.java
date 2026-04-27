package Lab08;

import java.util.ArrayList;
import java.util.List;

public class VideoAula extends ConteudoDigital implements Reproduzivel, Avaliavel {
    
    private String professor;
    private List<Integer> avaliacoes;

    public VideoAula(String titulo, int duracao, String professor){
        super(titulo, duracao);
        this.professor = professor;
        this.avaliacoes = new ArrayList<>();
    }

    @Override //duração em minutos para horas
    public double calcularCargaHoraria(){
        return getDuracaoEmMinutos() / 60.0;
    }

    @Override
    public void exibirDetalhes(){
        System.out.println("┌─ [VIDEOAULA] " + getTitulo());
        System.out.println("│  Professor : " + professor);
        System.out.printf("│  Duração   : %d min (%.1f h)%n",
        getDuracaoEmMinutos(), calcularCargaHoraria());
        System.out.printf("│  Avaliação : %.1f/10 (%d avaliações)%n",
        getMediaAvaliacoes(), avaliacoes.size());
        System.out.println("└─────────────────────────────────────");
    }

    @Override
    public void reproduzir(){
        System.out.println("Reproduzindo videoaula: \"" + getTitulo() + "\"");
    }

    @Override
    public void registrarAvaliacao(int nota){
        if (nota >= 0 && nota <= 10) {
            avaliacoes.add(nota);
            System.out.println("Avaliação " + nota + " registrada em \"" + getTitulo() + "\"");
        } else {
            System.out.println("Nota inválida. Use valores entre 0 e 10.");
        }
    }

    @Override
    public double getMediaAvaliacoes(){
        if(avaliacoes.isEmpty()) return 0.0;
        int soma = 0;
        for(int n : avaliacoes) soma += n;
        return (double) soma / avaliacoes.size();
    }
}
