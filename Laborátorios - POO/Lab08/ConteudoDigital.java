package Lab08;

public abstract class ConteudoDigital {
    
    private String titulo;
    private int duracaoEmMinutos;

    public ConteudoDigital(String titulo, int duracaoEmMinutos){
        this.titulo = titulo;
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public String getTitulo(){
        return titulo;
    }

    public int getDuracaoEmMinutos(){
        return duracaoEmMinutos;
    }

    public abstract double calcularCargaHoraria();
    public abstract void exibirDetalhes();
}
