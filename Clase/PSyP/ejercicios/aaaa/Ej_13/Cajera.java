package Ej_13;

import static java.lang.Thread.sleep;

public class Cajera {
    private String nombre;
    private int tiempo;
    public Cajera(String nombre) {
        this.nombre = nombre;
    }

    public void procesarCompra(Cliente cliente) {
        int tiempo_espera=1000;
        int[] tiempos = cliente.getTiempos();
        System.out.println("La cajera " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " + cliente.getNombre() + " EN EL TIEMPO " + tiempo + "segs");
        for (int i=0; i<tiempos.length; i++){
            tiempo = tiempo + tiempos[i];
            System.out.println("Procesando el producto " + (i+1) + " del " + cliente.getNombre() + " -> Tiempo: " + tiempo + "segs");
            try {
                sleep(tiempo_espera);
            } catch (InterruptedException e) {}
        }
        System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR " + cliente.getNombre() + " EN EL TIEMPO: " + tiempo + "segs");
    }
}
