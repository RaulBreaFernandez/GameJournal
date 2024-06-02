package ej_1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ej_1 {
    public static void main(String[] args) {
        try {
            InetAddress direccion = InetAddress.getByName("www.google.com"); //poniendo el url el metodo extrae solo el nombre y la ip

            System.out.println("-> Dirección IP de una URL, por nombre");
            System.out.println(direccion);

            System.out.println("\n->Nombre a partir de la dirección");
            System.out.println(direccion);

            System.out.println("\n->Direccion IP del actual host");
            direccion = InetAddress.getLocalHost(); //el metodo extrae solo la ip del ordenado usado
            System.out.println(direccion);

            System.out.println("\n->Nombre de LocalHost a partir de la dirección");
            System.out.println(direccion);

            System.out.println("\n->Nombre actual del LocalHost");
            System.out.println(direccion);

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
