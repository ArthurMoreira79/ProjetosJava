package Atividade02;

public class Carreta extends VeiculoCarga {
    
    public Carreta(String placa){
        super(placa, 25000.0, 70.0);
    }

    @Override
    public String descrever(){
        return "Carreta de placa " + this.placa + " - grande porte.";
    }

    //Tempo padrão + 10% para paradas e manobras
    @Override
    public double estimarTempoEntrega(double distanciaKm){
        return super.estimarTempoEntrega(distanciaKm) * 1.10;
    }
}
