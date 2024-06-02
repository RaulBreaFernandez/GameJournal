package ej_7;

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
        int opc=0, num1, num2, resultado;

        try {
            try {
                direcc = InetAddress.getLocalHost();
            } catch (UnknownHostException uhe) {
                System.err.println("Error en el host: " + uhe);
            }

            conexion = new Socket(direcc, 1234);
            DataInputStream entrada = new DataInputStream(conexion.getInputStream());
            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());

            System.out.println("\tMENÚ");
            System.out.println("1.Sumar");
            System.out.println("2.Restar");
            System.out.println("3.Multiplicar");
            System.out.println("4.Dividir");
            System.out.println("5.Salir");

            while (opc!=5) {
                System.out.println("\n\tOPERACIÓN");
                System.out.print("Introduzca opcion: ");
                opc = sc.nextInt();
                System.out.print("Introduzca primer número: ");
                num1 = sc.nextInt();
                System.out.print("Introduzca segundo número: ");
                num2 = sc.nextInt();

                salida.writeInt(opc);

                if (opc == 5) {
                    System.out.println("\n\nADIOS... :)");
                    opc = 1;
                    entrada.close();
                    salida.close();
                    conexion.close();
                    break;
                }

                salida.writeInt(num1);
                salida.writeInt(num2);

                resultado = entrada.readInt();
                System.out.println("\tResultado -> " + resultado);
            }

        }catch (IOException e) {
            System.out.println("Error cliente: " + e);;
        }
    }
}
