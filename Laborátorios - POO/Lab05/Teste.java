package Lab05;

public class Teste {
    
    public static void main(String[] args){

        Ponto2D p2 = new Ponto2D(10, 20);
        Ponto3D p3 = new Ponto3D(5, 5, 5);
        Pixel pix = new Pixel(1, 1, 150);

        System.out.println("Ponto 2D: " + p2);
        System.out.println("Ponto 3D: " + p3);
        System.out.println("Pixel: " + pix);

        pix.desloca(10, 10);
        System.out.println("Pixel apos desloca: " + pix);

        Ponto2D p2Clone = p2.clone();
        System.out.println("Clone eh igual ao original? " + p2.equals(p2Clone));
    }
}
