package Ej_18;

public class Transporte extends Thread{
    private String nombre;
    private int distancia;

    public Transporte(String nombre, int distancia) {
        this.nombre = nombre;
        this.distancia = distancia;
    }

    public void run(){
        for (int i=1; i<=this.distancia; i++)
            System.out.println(this.nombre + " recorre " + i + "km");
    }

    public static void main(String[] args) {
        Transporte auto = new Transporte("auto", 15);
        Transporte moto = new Transporte("moto", 12);
        Transporte camion = new Transporte("camión", 18);

        auto.start();
        moto.start();
        camion.start();

        try{
            auto.join();
            moto.join();
            camion.join();
        }catch(InterruptedException e) {}

        System.out.println("\nEl programa ha finalizado");
    }
}
