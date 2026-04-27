package Lab05;

public class Ponto2D {
    
    private double x;
    private double y;

    public Ponto2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Ponto2D(){
        this(0.0, 0.0);
    }
    public Ponto2D(Ponto2D p){
        this(p.getX(), p.getY());
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void desloca(double dx, double dy){
        this.x += dx;
        this.y += dy;
    }

    public Ponto2D somaPonto(double dx, double dy){
        return new Ponto2D(this.x + dx, this.y + dy);
    }

    @Override
    public String toString(){
        return String.format("(%.2f, %.2f)", x, y);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof Ponto2D)) return false;
        Ponto2D other = (Ponto2D) obj;
        return Double.compare(this.x, other.x) == 0 && Double.compare(this.y, other.y) == 0;
    }

    public Ponto2D clone(){
        return new Ponto2D(this);
    }
}
