package Lab09.Atividade01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorNotas {
    
    private Map<Integer, Double> notasAlunos;

    public GerenciadorNotas(){
        this.notasAlunos = new HashMap<>();
    }

    public void adicionarNota(int matricula, double nota){ //Adicionar ou Sobrescrever nota
        notasAlunos.put(matricula, nota);
    }

    public void removerAluno(int matricula){ //Remover Aluno
        if(notasAlunos.remove(matricula) != null){
            System.out.println("Aluno " + matricula + " removido.");
        } else{
            System.out.println("Matrícula " + " não encontrada.");
        }
    }

    public void exibirNotas(){ //Exibir todas as Notas
        if(notasAlunos.isEmpty()){
            System.out.println("Nenhuma nota cadastrada.");
            return;
        }
        System.out.println("--- Notas cadastradas ---");
        for (Map.Entry<Integer, Double> entry : notasAlunos.entrySet()){
            System.out.printf("Matrícula: %d | Nota: %.1f%n", entry.getKey(), entry.getValue());
        }
    }

    public void atualizarNota(int matricula, double novaNota){ //Atualizar nota somente se a matrícula já existe
        if(notasAlunos.containsKey(matricula)){
            notasAlunos.put(matricula, novaNota);
            System.out.println("Nota do aluno " + matricula + " atualizada para " + novaNota);
        } else{
            System.out.println("Matrícula " + matricula + " não encontrada. Use adicionarNota.");
        }
    }

    public void exibirMaiorNota(){ //Retorna maior nota e matrícula correspondente
        if(notasAlunos.isEmpty()){
            System.out.println("Nenhuma nota cadastrada.");
            return;
        }
        int matriculaMaior = -1;
        double maiorNota = -1;
        for(Map.Entry<Integer, Double> entry : notasAlunos.entrySet()){
            if(entry.getValue() > maiorNota){
                maiorNota = entry.getValue();
                matriculaMaior = entry.getKey();
            }
        }
        System.out.printf("Maior nota: %.1f | Matrícula: %d%n", maiorNota, matriculaMaior);
    }

    public double calcularMedia(){ //Média das notas da turma
        if(notasAlunos.isEmpty()) return 0.0;
        double soma = 0;
        for (double nota : notasAlunos.values()){
            soma += nota;
        }
        return soma / notasAlunos.size();
    }

    public List<Integer> alunosAcimaDe(double minimo){ // Alunos com nota acima de um valor informado
        List<Integer> resultado = new ArrayList<>();
        for (Map.Entry<Integer, Double> entry : notasAlunos.entrySet()){
            if(entry.getValue() > minimo){
                resultado.add(entry.getKey());
            }
        }
        return resultado;
    }
}
