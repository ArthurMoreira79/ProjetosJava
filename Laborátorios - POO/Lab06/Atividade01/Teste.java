package Atividade01;

public class Teste {
    
    public static void main(String[] args){
        Gerente g = new Gerente("Carlos Lima", 8000.00, "clima", "s3nh@123");
        Desenvolvedor d = new Desenvolvedor("Marina Souza", 6500.00, 42);
        Secretaria s = new Secretaria("Patricia Rocha", 3200.00, 305);
 
        g.mostraDados();
        System.out.println();
        d.mostraDados();
        System.out.println();
        s.mostraDados();
        
        System.out.println("\n--- Reajuste do vale refeicao ---");
        System.out.printf("Vale atual:  R$ %.2f%n", Funcionario.getValeRefeicaoDiario());
        Funcionario.reajustaValeRefeicaoDiario(18.00);
        System.out.printf("Novo vale:   R$ %.2f%n", Funcionario.getValeRefeicaoDiario());
 
        System.out.println("\n--- Bonificacoes ---");
        Funcionario[] equipe = { g, d, s };
        for (Funcionario f : equipe) {
            System.out.printf("%-20s -> Bonificacao: R$ %.2f%n",
                    f.getNome(), f.calculaBonificacao());
        }
    }
}
