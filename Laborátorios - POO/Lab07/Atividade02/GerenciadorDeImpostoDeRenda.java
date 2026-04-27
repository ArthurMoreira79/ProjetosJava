package Lab07.Atividade02;

public class GerenciadorDeImpostoDeRenda{
    
    private double total;

    public void adiciona(Tributavel t){
        System.out.println("Adicionando tributavel: " + t);
        System.out.printf(" Tributo calculado: R$ %.2f%n", t.calculaTributos());
        this.total += t.calculaTributos();
    }

    public double getTotal(){
        return this.total;
    }
}
