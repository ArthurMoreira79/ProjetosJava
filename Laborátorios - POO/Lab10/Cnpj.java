package Lab10;

public class Cnpj {
    
    private String valor;

    public Cnpj(String valor){
        this.valor = valor;
    }

    public String getValor(){
        return this.valor;
    }

    public void setValor(String valor){
        this.valor = valor;
    }

    public boolean ehValido(){
        return primeiroDigitoVerificador() == primeiroDigitoCorreto() && segundoDigitoVerificador() == segundoDigitoCorreto();
    }

    private int primeiroDigitoCorreto(){
        return 0; //Calcula o primeiro digito verificador correto para o cnpj
    }

    private int primeiroDigitoVerificador(){
        return 0; //Extrai o primeiro digito verificador do cnpj armazenado
    }

    private int segundoDigitoCorreto(){
        return 0; //Calcula o segundo digito verificador correto para o cnpj
    }

    private int segundoDigitoVerificador(){
        return 0; //Extrai o segundo digito verificador do cnpj armazenado
    }

    @Override
    public String toString(){
        return this.valor;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Cnpj cnpj = (Cnpj) o;
        return valor != null && valor.equals(cnpj.valor);
    }

    @Override
    public int hashCode(){
        return valor != null ? valor.hashCode() : 0;
    }
}
