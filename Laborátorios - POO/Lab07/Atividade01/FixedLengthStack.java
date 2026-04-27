package Lab07.Atividade01;

public class FixedLengthStack implements ISimpleStack {
    
    private char[] dadosDaPilha;
    private int topo;

    public FixedLengthStack(int tamanho){
        this.dadosDaPilha = new char[tamanho];
        this.topo = 0;
    }

    @Override
    public void push(char dado){
        if(isFull()){
            System.out.println("Pilha Cheia");
            return;
        }
        this.dadosDaPilha[topo++] = dado;
    }

    @Override
    public char pop(){
        if(isEmpty()){
            System.out.println("Pilha Vazia");
            return (char) 0;
        }
        return this.dadosDaPilha[--topo];
    }

    @Override
    public boolean isEmpty(){
        return this.topo ==0;
    }

    @Override
    public boolean isFull(){
        return this.topo == this.dadosDaPilha.length;
    }

    @Override
    public void reset(){
        this.topo = 0;
    }

    @Override
    public char peek(){
        if(isEmpty()){
            System.out.println("Pilha Vazia");
            return (char) 0;
        }
        return this.dadosDaPilha[topo - 1];
    }

    @Override
    public int size(){
        return this.topo;
    }
}
