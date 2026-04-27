package Lab02;

public class TesteConta {
    public static void main(String[] args){

        Cliente cliente = new Cliente("Arthur Moreira", "1234");

        Conta conta = new Conta(2001, 9001, cliente, 4321, 800.0);

        System.out.println("=== Conta criada ===");
        System.out.println(conta);

        System.out.println("\n===Dados do cartão ===");
        System.out.println(conta.getCartao());

        conta.deposita(500.0);
        conta.saca(200.0);

        System.out.println("\n=== Após depositar R$500 e sacar R$200 ===");
        System.out.println(conta);

        conta.alterarCartao(9999, cliente);

        System.out.println("\nApós a renovação do cartão ===");
        System.out.println(conta);
        System.out.println(conta.getCartao());
    }
}
