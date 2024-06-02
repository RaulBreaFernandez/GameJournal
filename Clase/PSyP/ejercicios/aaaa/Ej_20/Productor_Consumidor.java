package Ej_20;

public class Productor_Consumidor {
    public static void main (String[] args) {
        Monitor m = new Monitor(5); //
        Productor p = new Productor(m, 10, 200); //monitor, caracteres producir, tiempo espera
        Consumidor c = new Consumidor(m, 10, 400);

        p.start();
        c.start();
    }
}
