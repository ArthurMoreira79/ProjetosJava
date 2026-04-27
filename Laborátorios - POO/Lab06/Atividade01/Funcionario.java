package Atividade01;

public class Funcionario {
    
    private String nome;
    private double salario;
    private static double valeRefeicaoDiario = 15.00;

    public Funcionario(String nome, double salario){
        this.nome = nome;
        this.salario = salario;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public double getSalario(){
        return this.salario;
    }

    public void setSalario(double salario){
        this.salario = salario;
    }

    public static void reajustaValeRefeicaoDiario(double novoValor){
        valeRefeicaoDiario = novoValor;
    }

    public static double getValeRefeicaoDiario(){
        return valeRefeicaoDiario;
    }

    public double calculaBonificacao(){
        return this.salario * 0.10;
    }

    public void mostraDados(){
        System.out.println("=== Funcionario ===");
        System.out.println("Nome:        " + this.nome);
        System.out.printf("Salario:     R$ %.2f%n", this.salario);
        System.out.printf("Bonificacao: R$ %.2f%n", this.calculaBonificacao());
        System.out.printf("Vale/dia:    R$ %.2f%n", valeRefeicaoDiario);
    }
}
