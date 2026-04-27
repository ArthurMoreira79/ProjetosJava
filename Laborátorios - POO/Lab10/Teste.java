package Lab10;

import java.text.NumberFormat;
import java.util.Locale;

public class Teste { //Teste de Relatório de Dividas
    
    public static void main(String[] args) {
        Cnpj cnpj = new Cnpj("00.000.000/0001-01");
 
        Divida divida = new Divida();
        divida.setCredor("Credor 1");
        divida.setCnpjCredor(cnpj);
        divida.setTotal(3000);
 
        Pagamento pagamento1 = new Pagamento();
        pagamento1.setValor(100);
        divida.registra(pagamento1);
 
        NumberFormat formatadorBrasileiro = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        NumberFormat formatadorAmericano = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
 
        RelatorioDeDivida relatorio = new RelatorioDeDivida(divida);
 
        System.out.println("=== Relatório em formato brasileiro ===");
        relatorio.geraRelatorio(formatadorBrasileiro);
 
        System.out.println("\n=== Relatório em formato americano ===");
        relatorio.geraRelatorio(formatadorAmericano);
    }
}
