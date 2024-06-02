package ej_2;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ej_2 {
    public static void main(String args[]) {
        InetAddress dir = null;

        try{
            System.out.println("SALIDA PARA LOCAL HOST");
            dir = InetAddress.getByName("DESKTOP-BQIR0EI");
            pruebaMetodos(dir);

            System.out.println("\n=====================================================\n");

            System.out.println("SALIDA PARA URL");
            dir = InetAddress.getByName("www.google.com");
            pruebaMetodos(dir);

        }catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
    }

    public static void pruebaMetodos(InetAddress dir) {
        System.out.println("metodo getByName: " + dir);
        InetAddress dir2;

        try{
            dir2 = InetAddress.getLocalHost();
            System.out.println("metodo localHost: " + dir2);
        }catch (UnknownHostException e) {
            System.out.println("metodo getHostName: " + dir.getHostName()); //extrae el nombre de la url mandada
            System.out.println("metodo getHostAdress: " + dir.getHostAddress()); //extrae el numero de ip
            System.out.println("metodo toString: " + dir.toString()); //pasa a string la info
            System.out.println("metodo getCanonicalHostName: " + dir.getCanonicalHostName()); //muestra el nombre de la .net
        }
    }
}
