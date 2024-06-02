package ej_5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        InetAddress direcc = null;
        Socket sckt = null;

        try {
            try {
                direcc = InetAddress.getByName("localhost");
                System.out.println("Dirección IP del servidor -> " + direcc);
            } catch (UnknownHostException uhe) {
                System.out.println("Host no encontrado: " + uhe);
                System.exit(-1);
            }

            int puerto = 1234;
            sckt = new Socket(direcc, puerto);;
            DataInputStream dis = new DataInputStream(sckt.getInputStream());
            DataOutputStream dos = new DataOutputStream(sckt.getOutputStream());

            System.out.println("\n\tCALCULAR MCD");
            System.out.print("\nIntroduce el primer número: ");
            int num1 = sc.nextInt();
            System.out.print("Introduce el segundo número: ");
            int num2 = sc.nextInt();

            dos.writeLong(num1);
            dos.writeLong(num2);

            if (num1!= 0 && num2!=0) {
                long resultado = dis.readLong();
                System.out.println("\n\tResultado = " + resultado);

            }

            if (num1 != 0) {
                System.out.println("\n-----------Cliente cerrado-----------");
            } else {
                System.out.println("\n-----------Cliente y servidor cerrados-----------");
            }

        } catch (IOException e) {
            System.err.println("(cliente) Se ha producido la excepción: " + e);
        } finally {
            try {
                if (sckt != null)
                    sckt.close();
            } catch (IOException ioe) {
                System.err.println("Error al cerrar el socket: " + ioe);
            }
        }
    }
}
