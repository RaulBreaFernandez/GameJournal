package ej_9;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        InetAddress direcc = null;
        Socket conexion = null;
        String palabra="hola"; //esto es lo q escribe el cliente, lo q le va a mandar al servidor

        try{
            try{
                direcc = InetAddress.getLocalHost();
            }catch (UnknownError ue){
                System.err.println("Error host: " + ue);
            }

            conexion = new Socket(direcc, 1234);
            DataInputStream entrada = new DataInputStream(conexion.getInputStream());
            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());

            do{
                System.out.print("Escriba algo: ");
                palabra = sc.nextLine();

                salida.writeUTF(palabra);

                if (palabra.equalsIgnoreCase("adios")) {
                    System.out.println("\nADIOS... :)");
                    break;
                }

                String respuesta = entrada.readUTF();
                System.out.println("Respuesta servidor -> " + respuesta);
                System.out.println(); //esto es solo estetico

            }while (!palabra.equalsIgnoreCase("adios"));

            sc.close();
            entrada.close();
            salida.close();
            conexion.close();

        }catch (IOException e) {
            System.err.println("(cliente) error -> " + e);
        }
    }
}
