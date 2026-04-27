package Atividade02;

import java.util.ArrayList;

public class SimladorLogistica {
    
    public static void main(String[] args){

        ArrayList<VeiculoCarga> frota = new ArrayList<>();
        frota.add(new Caminhao("ABC-1234"));
        frota.add(new Furgao("DEF-5678"));
        frota.add(new Carreta("GHI-9012"));

        double distancia = 300.0;

        for(VeiculoCarga veiculo : frota){
            double carga80 = veiculo.capacidadeKg * 0.80;
            System.out.println("============================================");
            System.out.println(veiculo.descrever());
            System.out.printf("Carregando %.0f kg (80%% da capacidade):%n", carga80);
            veiculo.carregarCarga(carga80);
            double tempo = veiculo.estimarTempoEntrega(distancia);
            System.out.printf("Tempo estimado para %.0f km: %.2f horas%n", distancia, tempo);
        }
        System.out.println("============================================");
    }
}
