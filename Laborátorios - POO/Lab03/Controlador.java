package Lab03;

public class Controlador {

    private String nome;

    public Controlador(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void acionar(){
        System.out.println("Controlador '" + this.nome + "' acionado!");
    }

    @Override
    public String toString(){
        return "Controlador(Nome: " + this.nome + ")\n";
    }
}
