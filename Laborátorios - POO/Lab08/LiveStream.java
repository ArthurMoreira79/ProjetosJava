package Lab08;

import java.util.ArrayList;
import java.util.List;

public class LiveStream extends ConteudoDigital implements Reproduzivel, Avaliavel, ChatAoVivo {
    private String apresentador;
    private List<Integer> avaliacoes;
    private List<String> mensagensChat;

    public LiveStream(String titulo, int duracao, String apresentador) {
        super(titulo, duracao);
        this.apresentador = apresentador;
        this.avaliacoes = new ArrayList<>();
        this.mensagensChat = new ArrayList<>();
    }

    @Override
    public double calcularCargaHoraria() {
        return getDuracaoEmMinutos() / 60.0;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("┌─ [LIVE] " + getTitulo());
        System.out.println("│  Apresentador: " + apresentador);
        System.out.printf("│  Duração     : %d min (%.1f h)%n",
        getDuracaoEmMinutos(), calcularCargaHoraria());
        System.out.printf("│  Avaliação   : %.1f/10 (%d avaliações)%n",
        getMediaAvaliacoes(), avaliacoes.size());
        System.out.println("│  Msgs no chat: " + mensagensChat.size());
        System.out.println("└─────────────────────────────────────");
    }

    @Override
    public void reproduzir() {
        System.out.println("Iniciando live: \"" + getTitulo() + "\" com " + apresentador);
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

    @Override
    public void enviarMensagemChat(String mensagem) {
        mensagensChat.add(mensagem);
        System.out.println("[Chat \"" + getTitulo() + "\"] " + mensagem);
    }
}