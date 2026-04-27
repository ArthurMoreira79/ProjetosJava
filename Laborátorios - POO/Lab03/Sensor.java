package Lab03;

public class Sensor {

    private int id;
    private String tipo;
    private Controlador controlador;

    public Sensor(int id, String tipo){
        this.id = id;
        this.tipo = tipo;
    }

    public int getId(){
        return this.id;
    }
    
    public String getTipo(){
        return this.tipo;
    }

    public Controlador getControlador(){
        return this.controlador;
    }

    public void associarControlador(Controlador c){
        this.controlador = c;
    }

    public void desassociarControlador(){
        this.controlador = null;
    }

    public void detectarEvento(){
        System.out.println(this + "detectou um evento!");
        if(this.controlador != null){
            this.controlador.acionar();
        } else{
            System.out.println("Nenhum controlador associado a este sensor.");
        }
    }

    @Override
    public String toString(){
        return "Sensor(ID: " + this.id + ", tipo: " + this.tipo + ", controlador: " + (controlador != null ? controlador.getNome() : "nenhum") + ")\n";
    }
}
