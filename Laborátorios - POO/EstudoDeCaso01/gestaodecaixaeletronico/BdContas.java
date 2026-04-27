package EstudoDeCaso01.gestaodecaixaeletronico;

import EstudoDeCaso01.gestaodecontas.Conta;

public class BdContas {
    
    private Conta[] contas;
    private int numeroDeContas;
 
    public BdContas(int capacidade) {
        this.contas = new Conta[capacidade];
        this.numeroDeContas = 0;
    }

    public boolean adicionaConta(Conta conta){
        if(this.numeroDeContas == this.contas.length || this.buscaConta(conta.getNumero()) != null){
            return false;
        }
        this.contas[this.numeroDeContas++] = conta;
        return true;
    }

    public Conta buscaConta(int numero){
        for(int i = 0; i < this.numeroDeContas; i++){
            if(numero == this.contas[i].getNumero()){
                return this.contas[i];
            }
        }
        return null;
    }
}
