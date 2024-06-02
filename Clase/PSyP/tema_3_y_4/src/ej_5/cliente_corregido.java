package ej_5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class cliente_corregido {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        InetAddress direcc = null;
        Socket conexion;
        int num1=1, num2;

        try{
            try{
                direcc = InetAddress.getLocalHost();
            }catch (UnknownHostException uhe) {
                System.err.println("Error de conexion: " + uhe);
            }

            conexion = new Socket(direcc, 1234);
            DataInputStream entrada = new DataInputStream(conexion.getInputStream());
            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());

            while (num1 != 0) {
                System.out.print("Introduzca primer numero: ");
                num1 = sc.nextInt();
                System.out.print("Introduzca segundo numero: ");
                num2 = sc.nextInt();

                salida.writeInt(num1);
                salida.writeInt(num2);

                if (num1 == 0) {
                    System.out.println("ADIOS... :)");
                    break;
                }

                int resultado = entrada.readInt();
                System.out.println("Resultado -> " + resultado);
                System.out.println(); //esto es solo estetico
            }

            sc.close();
            entrada.close();
            salida.close();
            conexion.close();
        }catch (IOException e) {
            System.out.println("(cliente) Error en el cliente: " + e);
        }
    }
}
