package Lab08;

import java.util.ArrayList;
import java.util.List;

public class Podcast extends ConteudoDigital implements Reproduzivel, Avaliavel{
    
    private String host;
    private List<String> convidados;
    private List<Integer> avaliacoes;
 
    public Podcast(String titulo, int duracao, String host) {
        super(titulo, duracao);
        this.host = host;
        this.convidados = new ArrayList<>();
        this.avaliacoes = new ArrayList<>();
    }
 
    public void adicionarConvidado(String convidado) {
        convidados.add(convidado);
    }
 
    @Override
    public double calcularCargaHoraria() {
        return getDuracaoEmMinutos() / 60.0;
    }
 
    @Override
    public void exibirDetalhes() {
        System.out.println("┌─ [PODCAST] " + getTitulo());
        System.out.println("│  Host      : " + host);
        System.out.println("│  Convidados: " + (convidados.isEmpty() ? "nenhum" : convidados));
        System.out.printf("│  Duração   : %d min (%.1f h)%n",
        getDuracaoEmMinutos(), calcularCargaHoraria());
        System.out.printf("│  Avaliação : %.1f/10 (%d avaliações)%n",
        getMediaAvaliacoes(), avaliacoes.size());
        System.out.println("└─────────────────────────────────────");
    }
 
    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo podcast: \"" + getTitulo() + "\"");
    }
 
    @Override
    public void registrarAvaliacao(int nota) {
        if (nota >= 0 && nota <= 10) {
            avaliacoes.add(nota);
            System.out.println("Avaliação " + nota + " registrada em \"" + getTitulo() + "\"");
        } else {
            System.out.println("Nota inválida. Use valores entre 0 e 10.");
        }
    }
 
    @Override
    public double getMediaAvaliacoes() {
        if (avaliacoes.isEmpty()) return 0.0;
        int soma = 0;
        for (int n : avaliacoes) soma += n;
        return (double) soma / avaliacoes.size();
    }
}
