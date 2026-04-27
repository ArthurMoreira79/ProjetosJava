package Lab01.Atividade01;


public class Pessoa {
    
    private String nome;
    private String cpf;

    public Pessoa(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome(){
        return nome;
    }

    public String getCpf(){
        return cpf;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    @Override
    public String toString(){
        return "Pessoa [CPF: " + cpf + ", Nome: " + nome + "]";
    }
}
