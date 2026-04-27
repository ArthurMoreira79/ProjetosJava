package Lab01.Atividade02;

public class Automovel {

    private String placa;
    private double kmTotal;
    private double capacidadeDoTanque;
    private double eficiencia;
    private double combustivelNoTanque;
    private int numeroDeViagensRealizadas;

    public Automovel(String placa, double capacidadeDoTanque, double eficiencia){
        this.placa = placa;
        this.capacidadeDoTanque = capacidadeDoTanque;
        this.eficiencia = eficiencia;
    }

    public String getPlaca(){
        return placa;
    }

    public double getKmTotal(){
        return kmTotal;
    }

    public double getCapacidadeDoTanque(){
        return capacidadeDoTanque;
    }

    public double getEficiencia(){
        return eficiencia;
    }

    public double getCombustivelNoTanque(){
        return combustivelNoTanque;
    }

    public int getNumeroDeViagensRealizadas(){
        return numeroDeViagensRealizadas;
    }

    public void abastece(double quantidade){
        if(quantidade > 0){
            this.combustivelNoTanque += quantidade;

            if(this.getCombustivelNoTanque() > this.getCapacidadeDoTanque()){
                this.combustivelNoTanque = this.getCapacidadeDoTanque();
            }
        }
    }

    public void dirige(double distancia){
        if(distancia > 0){
            if(this.gastoDeCombustivel(distancia) > this.combustivelNoTanque){
                distancia = this.autonomia();
            }

            this.numeroDeViagensRealizadas++;
            this.kmTotal += distancia;
            this.combustivelNoTanque -= this.gastoDeCombustivel(distancia);
        }
    }

    public double gastoDeCombustivel(double distancia){
        return distancia / this.eficiencia;
    }

    public double autonomia(){
        return this.combustivelNoTanque * this.eficiencia;
    }

    public void computadorDeBordo(){
        System.out.println("===== Computador de Bordo =====");
        System.out.println("Placa:               " + placa);
        System.out.println("Km total percorrida: " + kmTotal + " km");
        System.out.println("Viagens realizadas:  " + numeroDeViagensRealizadas);
        System.out.println("Combustível no tanque: " + combustivelNoTanque + " L");
        System.out.println("Autonomia restante:  " + autonomia() + " km");
        System.out.println("Eficiência (consumo médio): " + eficiencia + " km/L");
        System.out.println("================================");
    }

    @Override
    public String toString(){
        return "Automovel [Placa: " + placa + ", KmTotal: " + kmTotal + ", Combustivel: " + combustivelNoTanque + " L" + ", Viagens: " + numeroDeViagensRealizadas + "]";
    }
}
