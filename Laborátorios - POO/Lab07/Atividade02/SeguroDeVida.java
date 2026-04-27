package Lab07.Atividade02;

public class SeguroDeVida implements Tributavel{
    
    private String beneficiario;

    public SeguroDeVida(String beneficiario){
        this.beneficiario = beneficiario;
    }

    @Override
    public double calculaTributos(){
        return 42.0;
    }

    @Override
    public String toString(){
        return "SeguroDeVida[beneficiario: " + this.beneficiario + "]";
    }
}
