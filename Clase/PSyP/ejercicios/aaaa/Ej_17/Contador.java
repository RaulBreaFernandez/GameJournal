package Ej_17;

public class Contador extends Thread{
    private int contador;
    private String name;
    private int limite;

    public Contador(String name, int limite){
        this.contador=0;
        this.name = name;
        this.limite = limite;
    }

    public void run() {
        for (this.contador=0; this.contador<=this.limite; contador++){
            System.out.println("Hilo " + this.name + ": " + contador);
        }
        System.out.println("\tHilo " + this.name + " ya ha acabado");
    }
    public static void main(String[] args) {
        Contador c1 = new Contador("Contador 1", 40);
        Contador c2 = new Contador("Contador 2", 50);
        Contador c3 = new Contador("Contador 3", 20);
        Contador c4 = new Contador("Contador 4", 70);

        c1.start();
        c2.start();
        c3.start();
        c4.start();

        try{
            c1.join();
            c2.join();
            c3.join();
            c4.join();
        }catch(InterruptedException e){}

        System.out.println("\nEl programa ha finalizado");
    }

}
