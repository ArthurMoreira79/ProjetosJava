package Atividade02;

public class Furgao extends VeiculoCarga {
    
    public Furgao(String placa){
        super(placa, 1500.0, 60.0);
    }

    @Override
    public String descrever(){
        return "Furgao de placa " + this.placa + "com capacidade de " + this.capacidadeKg + " kg";
    }

    //Alerta se o peso > 90% da capacidade; Bloqueia se > 100%
    @Override
    public void carregarCarga(double peso){
        if(peso > this.capacidadeKg){
            System.out.println("Carga exede a capacidade permitida.");
        } else if(peso >  this.capacidadeKg * 0.90){
            this.cargaAtualKg = peso;
            System.out.println("Carga proxima ao limite legal. Risco de multa.");
        } else{
            this.cargaAtualKg = peso;
            System.out.println("Carga carregada com sucesso.");
        }
    }
}
