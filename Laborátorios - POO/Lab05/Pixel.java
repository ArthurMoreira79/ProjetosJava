package Lab05;

public class Pixel extends Ponto2D {
    
    private int cor;

    public Pixel(double x, double y, int cor){
        super(x, y);
        this.cor = cor % 100;
    }

    public Pixel(){
        super();
        this.cor = 0;
    }

    public int getCor(){
        return this.cor;
    }

    public void mudaCor(int novaCor){
        this.cor = novaCor % 100;
    }

    @Override
    public String toString(){
        return super.toString() + " Cor: " + cor;
    }
}
