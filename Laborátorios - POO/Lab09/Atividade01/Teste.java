package Lab09.Atividade01;

import java.util.List;

public class Teste {
    
    public static void main(String[] args){

        GerenciadorNotas gn = new GerenciadorNotas();

        //Adicionando alunos
        gn.adicionarNota(1001, 8.5);
        gn.adicionarNota(1002, 6.0);
        gn.adicionarNota(1003, 9.7);
        gn.adicionarNota(1004, 4.3);
        gn.adicionarNota(1005, 7.8);
 
        System.out.println("=== NOTAS INICIAIS ===");
        gn.exibirNotas();
 
        System.out.println("\n=== ATUALIZANDO NOTA (1002: 6.0 -> 7.5) ===");
        gn.atualizarNota(1002, 7.5);
        gn.atualizarNota(9999, 5.0); // matrícula inexistente
 
        System.out.println("\n=== REMOVENDO ALUNO 1004 ===");
        gn.removerAluno(1004);
        gn.removerAluno(9999); // inexistente
 
        System.out.println("\n=== NOTAS APÓS ALTERAÇÕES ===");
        gn.exibirNotas();
 
        System.out.println("\n=== MAIOR NOTA ===");
        gn.exibirMaiorNota();
 
        //Média
        System.out.println("\n=== MÉDIA DA TURMA ===");
        System.out.printf("Média: %.2f%n", gn.calcularMedia());
 
        //Alunos acima de um valor
        double corte = 7.0;
        System.out.println("\n=== ALUNOS COM NOTA ACIMA DE " + corte + " ===");
        List<Integer> aprovados = gn.alunosAcimaDe(corte);
        if (aprovados.isEmpty()) {
            System.out.println("Nenhum aluno encontrado.");
        } else {
            for (int mat : aprovados) {
                System.out.println("Matrícula: " + mat);
            }
        }
    }
}
