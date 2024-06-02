package ej_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class cliente_corregido {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        InetAddress direcc = null;
        Socket conexion;
        int numero=1;
        
            try {
                direcc = InetAddress.getLocalHost();
            } catch (UnknownHostException uhe) {
                System.err.println("Error en el host: " + uhe);
            }
            try {
            conexion = new Socket(direcc, 1234);
            DataInputStream entrada = new DataInputStream(conexion.getInputStream());
            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());

            while (numero != 0){
                System.out.println("Introduzca numero: ");
                numero = sc.nextInt();

                salida.writeInt(numero);

                if (numero == 0) {
                    System.out.println("ADIOOS... :)");
                    break;
                }

                String respuesta = entrada.readUTF();
                if (respuesta.equalsIgnoreCase("igual")) {
                    System.out.println("\nFELICIDADES, HAS GANADO");
                    break;
                } else {
                    System.out.println("El numero es " + respuesta);
                    System.out.println(); //Esto es solo estetico
                }
            }

            entrada.close();
            salida.close();
            conexion.close();
        }catch (IOException e) {
            System.out.println("Error cliente: " + e);;
        }
    }
}
