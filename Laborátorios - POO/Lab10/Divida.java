package Lab10;

public class Divida {
    
    private double total;
    private String credor;
    private Cnpj cnpjCredor = new Cnpj("");
    private Pagamentos pagamentos = new Pagamentos();

    public double getTotal() {
        return this.total;
    }
 
    public void setTotal(double total) {
        this.total = total;
    }
 
    public String getCredor() {
        return this.credor;
    }
 
    public void setCredor(String credor) {
        this.credor = credor;
    }
 
    public Cnpj getCnpjCredor() {
        return this.cnpjCredor;
    }
 
    public void setCnpjCredor(Cnpj cnpjCredor) {
        this.cnpjCredor = cnpjCredor;
    }
 
    public void registra(Pagamento pagamento) {
        this.pagamentos.registra(pagamento);
    }
 
    public double valorAPagar() {
        return this.total - this.pagamentos.getValorPago();
    }
}
