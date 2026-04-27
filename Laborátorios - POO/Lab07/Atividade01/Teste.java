package Lab07.Atividade01;

public class Teste {
    
    public static void main(String[] args){

        System.out.println("=== FixedLengthStack (tamanho 4) ===");
        ISimpleStack pilhaFixa = new FixedLengthStack(4);
 
        pilhaFixa.push('A');
        pilhaFixa.push('B');
        pilhaFixa.push('C');
        pilhaFixa.push('D');
        pilhaFixa.push('E'); // deve avisar que a pilha ta cheia
 
        System.out.println("Topo (peek): " + pilhaFixa.peek());
        System.out.println("Tamanho: " + pilhaFixa.size());
        System.out.println("Pop: " + pilhaFixa.pop());
        System.out.println("Pop: " + pilhaFixa.pop());
        System.out.println("Tamanho apos 2 pops: " + pilhaFixa.size());
 
        pilhaFixa.reset();
        System.out.println("Vazia apos reset? " + pilhaFixa.isEmpty());
 
        System.out.println("\n=== DynamicStack (tamanho inicial 2) ===");
        ISimpleStack pilhaDinamica = new DynamicStack(2);
 
        pilhaDinamica.push('X');
        pilhaDinamica.push('Y');
        pilhaDinamica.push('Z'); // dobra o array internamente
        pilhaDinamica.push('W');
 
        System.out.println("isFull() sempre false: " + pilhaDinamica.isFull());
        System.out.println("Tamanho: " + pilhaDinamica.size());
        System.out.println("Topo (peek): " + pilhaDinamica.peek());
 
        System.out.println("Desempilhando tudo:");
        while (!pilhaDinamica.isEmpty()) {
            System.out.print(pilhaDinamica.pop() + " ");
        }
        System.out.println();
        pilhaDinamica.pop(); // deve avisar que a pilha ta vazia
    }
}
