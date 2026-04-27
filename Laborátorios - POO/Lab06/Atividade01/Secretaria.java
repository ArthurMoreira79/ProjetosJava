package Atividade01;

public class Secretaria extends Funcionario {
    
    private int ramal;

    public Secretaria(String nome, double salario, int ramal){
        super(nome, salario);
        this.ramal = ramal;
    }

    public int getRamal(){
        return this.ramal;
    }

    public void setRamal(int ramal){
        this.ramal = ramal;
    }

    @Override
    public void mostraDados(){
        super.mostraDados();
        System.out.println("Cargo:       Secretaria");
        System.out.println("Ramal:       " + this.ramal);
    }
}
