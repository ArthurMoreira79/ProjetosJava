package Lab01.Atividade01;

public class TestePessoa {
    public static void main(String[] args){
        Pessoa p1 = new Pessoa("123","Arthur");
        Pessoa p2 = new Pessoa("321", "Pedro");

        System.out.println("Dados da primeira pessoa");
        System.out.println("CPF: " + p1.getCpf());
        System.out.println("Nome: " + p1.getNome());

        System.out.println("-------------------------");

        System.out.println("Dados da segunda pessoa");
        System.out.println("CPF: " + p2.getCpf());
        System.out.println("Nome: " + p2.getNome());
    }
}
