package EstudoDeCaso01.testecaixaeletronico;

import EstudoDeCaso01.gestaodecontas.Cliente;
import EstudoDeCaso01.gestaodecontas.Conta;
import EstudoDeCaso01.gestaodecaixaeletronico.BdContas;
import EstudoDeCaso01.gestaodecaixaeletronico.Terminal;

public class TesteCaixaEletronico {
    
    public static void main(String[] args){

        Cliente c1 = new Cliente("111.111.111-11", "Ana Silva");
        Cliente c2 = new Cliente("222.222.222-22", "Bruno Costa");
        Cliente c3 = new Cliente("333.333.333-33", "Carla Mendes");
 
        Conta conta1 = new Conta(1001, c1, 1234, 1500.00);
        Conta conta2 = new Conta(1002, c2, 4321, 800.00);
        Conta conta3 = new Conta(1003, c3, 9999, 300.00);

        BdContas bd = new BdContas(10);
        bd.adicionaConta(conta1);
        bd.adicionaConta(conta2);
        bd.adicionaConta(conta3);

        Terminal terminal = new Terminal(bd);
        terminal.iniciaOperacao();
    }
}
