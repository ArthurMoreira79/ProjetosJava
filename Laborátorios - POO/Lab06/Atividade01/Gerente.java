package Atividade01;

public class Gerente extends Funcionario {
    
    private String usuario;
    private String senha;

    public Gerente(String nome, double salario, String usuario, String senha){
        super(nome, salario);
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario(){
        return this.usuario;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getSenha(){
        return this.senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    @Override
    public double calculaBonificacao(){
        return super.calculaBonificacao() + 500.00;
    }

    @Override
    public void mostraDados(){
        super.mostraDados();
        System.out.println("Cargo:       Gerente");
        System.out.println("Usuario:     " + this.usuario);
    }
}
