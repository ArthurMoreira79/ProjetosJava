package Lab07.Atividade02;

public class Teste {
    
    public static void main(String[] args) {
 
        ContaCorrente cc1 = new ContaCorrente(1001, "Ana Silva", 5000.00);
        ContaCorrente cc2 = new ContaCorrente(1002, "Bruno Costa", 12000.00);
        SeguroDeVida sv1 = new SeguroDeVida("Carlos Mendes");
        SeguroDeVida sv2 = new SeguroDeVida("Diana Lima");
 
        GerenciadorDeImpostoDeRenda gir = new GerenciadorDeImpostoDeRenda();
 
        // Aceita qualquer Tributavel — ContaCorrente ou SeguroDeVida
        gir.adiciona(cc1);
        gir.adiciona(cc2);
        gir.adiciona(sv1);
        gir.adiciona(sv2);
 
        System.out.println("-------------------------------------------");
        System.out.printf("Total de tributos a pagar: R$ %.2f%n", gir.getTotal());
    }
}
