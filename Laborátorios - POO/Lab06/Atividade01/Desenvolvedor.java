package Atividade01;

public class Desenvolvedor extends Funcionario {
    
    private int estacaoDeTrabalho;

    public Desenvolvedor(String nome, double salario, int estacaoDeTrabalho){
        super(nome, salario);
        this.estacaoDeTrabalho = estacaoDeTrabalho;
    }

    public int getEstacaoDeTrabalho(){
        return this.estacaoDeTrabalho;
    }

    public void setEstacaoDeTrabalho(int estacaoDeTrabalho){
        this.estacaoDeTrabalho = estacaoDeTrabalho;
    }

    @Override
    public void mostraDados(){
        super.mostraDados();
        System.out.println("Cargo:       Desenvolvedor");
        System.out.println("Estacao:     " + this.estacaoDeTrabalho);
    }
}
