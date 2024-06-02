package Ej_13;

public class Main {
    public static void main (String[] args) {

        Cliente cliente1 = new Cliente("Cliente 1", new int[] {2,2,1,5,2,3});
        Cliente cliente2 = new Cliente("Cliente 2", new int[] {1,3,5,1,1});

        Cajera cajera1 = new Cajera("Cajera 1");
        Cajera cajera2 = new Cajera("Cajera 2");


        cajera1.procesarCompra(cliente1);
        System.out.println();
        cajera2.procesarCompra(cliente2);
        System.out.println();
        cajera1.procesarCompra(cliente2);

        /*
        //cajera 1
        int tiempos[] = cliente1.getTiempos();
        int tiempo=0;
        System.out.println("La cajera " + cajera1.getNombre() + " COMIENZA A PROCESAR LA COMPRA DEL " + cliente1.getNombre() + " EN EL TIEMPO " + tiempo + "segs");
        for (int i=0; i<tiempos.length; i++){
            tiempo = tiempo + tiempos[i];
            System.out.println("Procesando el producto " + (i+1) + " -> Tiempo: " + tiempo + "segs");
        }
        System.out.println("La cajera " + cajera1.getNombre() + " HA TERMINADO DE PROCESAR " + cliente1.getNombre() + " EN EL TIEMPO: " + tiempo + "segs");


        System.out.println();


        //cajera 2
        int tiempos2[] = cliente2.getTiempos();
        int tiempo2 = 0;
        System.out.println("La cajera " + cajera2.getNombre() + " COMIENZA A PROCESAR LA COMPRA DEL " + cliente2.getNombre() + " EN EL TIEMPO " + tiempo2 + "segs");
        for (int i=0; i<tiempos2.length; i++){
            tiempo2 += tiempos2[i];
            System.out.println("Procesando el producto " + (i+1) + " -> Tiempo: " + tiempo2 + "segs");
        }
        System.out.println("La cajera " + cajera2.getNombre() + " HA TERMINADO DE PROCESAR " + cliente2.getNombre() + " EN EL TIEMPO: " + tiempo2 + "segs");


        System.out.println("\n\tEl programa ha finalizado");
        */

    }

}
