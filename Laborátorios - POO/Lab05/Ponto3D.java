package Lab05;

public class Ponto3D extends Ponto2D {
    
    private double z;

    public Ponto3D(double x, double y, double z){
        super(x, y);
        this.z = z;
    }

    public Ponto3D(){
        super();
        this.z = 0.0;
    }

    public double getZ(){
        return this.z;
    }

    public void desloca(double dx, double dy, double dz){
        super.desloca(dx, dy);
        this.z += dz;
    }

    public Ponto3D somaPonto(double dx, double dy, double dz){
        return new Ponto3D(super.getX() + dx, super.getY() + dy, this.z + dz);
    }

    @Override
    public String toString(){
        return String.format("(%.2f, %.2f, %.2f)", super.getX(), super.getY(), z);
    }
}
