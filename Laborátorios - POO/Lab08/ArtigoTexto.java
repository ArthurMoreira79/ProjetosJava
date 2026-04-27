package Lab08;

import java.util.ArrayList;
import java.util.List;

public class ArtigoTexto extends ConteudoDigital implements Imprimivel, Avaliavel {
    private String autor;
    private int paginas;
    private List<Integer> avaliacoes;

    public ArtigoTexto(String titulo, int paginas, String autor) {
        super(titulo, paginas * 2); // O construtor recebe páginas; a duração é estimada em 2 min por página
        this.autor = autor;
        this.paginas = paginas; 
        this.avaliacoes = new ArrayList<>();
    }

    // Carga horária baseada no tempo estimado de leitura
    @Override
    public double calcularCargaHoraria() {
        return getDuracaoEmMinutos() / 60.0;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("┌─ [ARTIGO] " + getTitulo());
        System.out.println("│  Autor     : " + autor);
        System.out.println("│  Páginas   : " + paginas);
        System.out.printf("│  Leitura   : ~%d min (%.1f h)%n",
        getDuracaoEmMinutos(), calcularCargaHoraria());
        System.out.printf("│  Avaliação : %.1f/10 (%d avaliações)%n",
        getMediaAvaliacoes(), avaliacoes.size());
        System.out.println("└─────────────────────────────────────");
    }

    @Override
    public void imprimir() {
        System.out.println("Imprimindo artigo: \"" + getTitulo() + "\" (" + paginas + " páginas)");
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