package Lab01.Atividade02;

public class TesteAutomovel {
    public static void main(String[] args){

        Automovel a1 = new Automovel("PTY5B23", 50.00, 10.00);

        a1.abastece(50);

        System.out.println("Combustivel após abastecer: " + a1.getCombustivelNoTanque() + " L");

        a1.dirige(400);

        System.out.println("Combustivel após 400 km: " + a1.getCombustivelNoTanque() + " L");
        System.out.println("Viagens realizadas: " + a1.getNumeroDeViagensRealizadas());
        System.out.println("Km total: " + a1.getKmTotal() + " Km");

        a1.dirige(150);

        System.out.println("\nApós tentar diigir 150 km (autonomia era de 100 km):");
        System.out.println("Km total: " + a1.getKmTotal() + " Km");

        System.out.println();
        a1.computadorDeBordo();
    }
}
