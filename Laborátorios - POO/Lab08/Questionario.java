package Lab08;

public class Questionario extends ConteudoDigital implements Imprimivel {
    private int quantidadeDePerguntas;

    // Duração estimada: 3 minutos por pergunta
    public Questionario(String titulo, int perguntas) {
        super(titulo, perguntas * 3);
        this.quantidadeDePerguntas = perguntas;
    }

    // Carga horária baseada no tempo médio de resposta
    @Override
    public double calcularCargaHoraria() {
        return getDuracaoEmMinutos() / 60.0;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("┌─ [QUESTIONÁRIO] " + getTitulo());
        System.out.println("│  Perguntas : " + quantidadeDePerguntas);
        System.out.printf("│  Tempo est.: ~%d min (%.1f h)%n",
        getDuracaoEmMinutos(), calcularCargaHoraria());
        System.out.println("└─────────────────────────────────────");
    }

    @Override
    public void imprimir() {
        System.out.println("🖨 Imprimindo questionário: \"" + getTitulo()
        + "\" (" + quantidadeDePerguntas + " perguntas)");
    }
}