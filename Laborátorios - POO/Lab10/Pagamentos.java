package Lab10;

import java.util.ArrayList;
import java.util.Calendar;

public class Pagamentos {
    
    private double valorPago;
    private ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();

    public void registra(Pagamento pagamento){
        double valor = pagamento.getValor();
        if(valor < 0){
            throw new IllegalArgumentException("Valor invalido para pagamento.");
        }
        if(valor > 100){
            valor -= 8;
        }
        this.valorPago += valor;
        this.pagamentos.add(pagamento);
    }

    public double getValorPago(){
        return this.valorPago;
    }

    public boolean foiRealizado(Pagamento pagamento){
        return this.pagamentos.contains(pagamento);
    }

    //Pagamentos filtrados antes de uma data especifica
    public ArrayList<Pagamento> pagamentosAntesDe(Calendar data){
        ArrayList<Pagamento> pagamentosFiltrados = new ArrayList<Pagamento>();
        for(Pagamento pagamento : this.pagamentos){
            if(pagamento.getData().before(data)){
                pagamentosFiltrados.add(pagamento);
            }
        }
        return pagamentosFiltrados;
    }

    //Pagamentos filtrados por valor Maior que outro
    public ArrayList<Pagamento> pagamentosComValorMaiorQue(double valorMinimo){
        ArrayList<Pagamento> pagamentosFiltrados = new ArrayList<Pagamento>();
        for(Pagamento pagamento : this.pagamentos){
            if(pagamento.getValor() > valorMinimo){
                pagamentosFiltrados.add(pagamento);
            }
        }
        return pagamentosFiltrados;
    }

    //Pagamentos filtrados por Pagador(Cnpj do Pagador)
    public ArrayList<Pagamento> pagamentosDo(String cnpjPagador){
        ArrayList<Pagamento> pagamentosFiltrados = new ArrayList<Pagamento>();
        for(Pagamento pagamento : this.pagamentos){
            if(pagamento.getCnpjPagador().equals(cnpjPagador)){
                pagamentosFiltrados.add(pagamento);
            }
        }
        return pagamentosFiltrados;
    }
}
