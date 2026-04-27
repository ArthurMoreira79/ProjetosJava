package Atividade02;

public class Caminhao extends VeiculoCarga {
    
    public Caminhao(String placa){
        super(placa, 10000.0, 80.0);
    }

    @Override
    public String descrever(){
        return "Caminhao de placa " + this.placa + " com capacidade de " + this.capacidadeKg + " kg";
    }

    //Se carregar mais de 80% da capacidade, tempo aumenta em 20%
    @Override
    public double estimarTempoEntrega(double distanciaKm){
        double tempoBase = super.estimarTempoEntrega(distanciaKm);
        if(this.cargaAtualKg > this.capacidadeKg * 0.80){
            return tempoBase * 1.20;
        }
        return tempoBase;
    }
}
