package Lab01.Atividade03;

public class TestePopulacaoDeBaratas {
    public static void main(String[] args){
       
        System.out.println("=== Teste com 10 baratas iniciais ===");
        PopulacaoDeBaratas pop1 = new PopulacaoDeBaratas(10);
        System.out.println("Estado inicial: " + pop1);

        pop1.esperaUmTempo();
        System.out.println("Após procriar: " + pop1);

        pop1.pulverizar();
        System.out.println("Após pulverizar (1x): " + pop1);

        pop1.pulverizar();
        System.out.println("Após pulverizar (2x): " + pop1);

        System.out.println("\n=== Teste com construtor padrão (5 baratas) ===");
        PopulacaoDeBaratas pop2 = new PopulacaoDeBaratas();
        System.out.println("Estado inicial: " + pop2);
 
        pop2.esperaUmTempo();
        System.out.println("Após procriar:  " + pop2);
 
        pop2.pulverizar();
        pop2.pulverizar();
        System.out.println("Após pulverizar (2x): " + pop2);
    }
}
