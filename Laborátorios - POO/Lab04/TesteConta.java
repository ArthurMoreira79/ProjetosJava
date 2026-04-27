package Lab04;

public class TesteConta {
    
    public static void main(String[] args){

        Cliente cliente = new Cliente("Maria Silva", "123.456.789-00");
        System.out.println("Cliente criado: " + cliente);

        Conta conta = new Conta(1001, 5555, cliente, 1234, 1000.0);
        System.out.println("\nConta criada: " + conta);

        System.out.println("\n--- Realizando operações ---");
 
        conta.creditaValor(500.0, "Depósito em dinheiro");
        conta.creditaValor(200.0, "Transferência recebida");
        conta.debitaValor(150.0, 1234, "Pagamento de conta de luz");
        conta.debitaValor(80.0, 1234, "Pagamento de internet");
        conta.creditaValor(1000.0, "Salário");
        conta.debitaValor(300.0, 1234, "Compra no supermercado");

        System.out.println();
        conta.exibeExtrato();

        System.out.println("\n--- Tentativa com senha errada ---");
        boolean resultado = conta.debitaValor(100.0, 9999, "Saque indevido");
        System.out.println("Débito com senha errada efetuado? " + resultado);

        System.out.println("\n--- Testando limite da fila (mais de 10 lançamentos) ---");
        for (int i = 1; i <= 7; i++) {
        conta.creditaValor(10.0 * i, "Lançamento extra " + i);
        }

        System.out.println("\n--- Troca de cartão ---");
        System.out.println("Cartão atual: " + conta.getCartao());
        conta.alteraCartao(6666, cliente);
        System.out.println("Novo cartão:  " + conta.getCartao());

        System.out.println("\n--- Demonstração de Agregação ---");
        System.out.println("O cliente existe independentemente da conta:");
        System.out.println(cliente);
        conta = null;
        System.out.println("Conta encerrada (null). Cliente ainda acessível: " + cliente);
    }
}
