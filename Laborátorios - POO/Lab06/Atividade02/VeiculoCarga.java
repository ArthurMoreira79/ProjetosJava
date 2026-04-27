package Atividade02;

public class VeiculoCarga {
    
    protected String placa;
    protected double capacidadeKg;
    protected double velocidadeMediaKmH;
    protected double cargaAtualKg;

    public VeiculoCarga(String placa, double capacidadeKm, double velocidadeMediaKmH){
        this.placa = placa;
        this.capacidadeKg = capacidadeKm;
        this.velocidadeMediaKmH = velocidadeMediaKmH;
        this.cargaAtualKg = 0;
    }

    public void carregarCarga(double peso){
        if(peso > this.capacidadeKg){
            System.out.println("Carga excede a capacidade permitida.");
        } else{
            System.out.println("Carga caregada com sucesso.");
        }
    }

    public double estimarTempoEntrega(double distanciaKm){
        return distanciaKm / this.velocidadeMediaKmH;
    }

    public String descrever(){
        return "Veiculo generico com placa " + this.placa;
    }
}
