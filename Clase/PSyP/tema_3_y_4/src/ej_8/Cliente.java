package ej_8;

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
        String palabra;

        try{
            try{
                direcc = InetAddress.getLocalHost();
            }catch (UnknownError ue) {
                System.out.println("Error host: " + ue);
            }

            conexion = new Socket(direcc, 1234);
            DataInputStream entrada = new DataInputStream(conexion.getInputStream());
            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());

            do {
                System.out.print("Escriba algo: ");
                palabra = sc.nextLine();

                salida.writeUTF(palabra);

                if (palabra.equalsIgnoreCase("Adios")) {
                    System.out.println("\n\tAdiós... :)");
                    break;
                }

                String servidor = entrada.readUTF();
                System.out.println("El servidor dice -> " + servidor);
                System.out.println(); // esto es solo estetico

            }while(!palabra.equalsIgnoreCase("adios"));

            System.out.println("\nCerrando cliente");
            sc.close();
            entrada.close();
            salida.close();
            conexion.close();

        }catch(IOException e) {
            System.out.println("(Cliente) Error: " + e);
        }
    }
}
