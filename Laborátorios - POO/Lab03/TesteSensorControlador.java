package Lab03;

public class TesteSensorControlador {

    public static void main(String[] args){

        Sensor sensor = new Sensor(1, "PRESENÇA");

        Controlador controlador = new Controlador("Luz da Sala");

        System.out.println("=== Antes da associação ===");
        System.out.println(sensor);
        System.out.println(controlador);

        sensor.associarControlador(controlador);

        System.out.println("\n=== Após associação ===");
        System.out.println(sensor);
        System.out.println(controlador);

        System.out.println("\n=== Simulando detecção de evento ===");
        sensor.detectarEvento();

        System.out.println("\n=== Após desassociação ===");
        sensor.desassociarControlador();
        System.out.println(sensor);
        sensor.detectarEvento();
    }
}
