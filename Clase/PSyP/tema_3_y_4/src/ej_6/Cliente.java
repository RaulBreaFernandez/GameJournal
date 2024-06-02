package ej_6;

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
        long num=1;
        String resultado;

        try {
            try {
                direcc = InetAddress.getLocalHost();
            } catch (UnknownHostException sce) {
                System.out.println("Host no encontrado: " + sce);
            }

            sckt = new Socket(direcc, 1234);
            DataInputStream dis = new DataInputStream(sckt.getInputStream());
            DataOutputStream dos = new DataOutputStream(sckt.getOutputStream());

            do {
                System.out.print("Introduce un número (o 0 para salir): ");
                num = sc.nextLong();

                dos.writeLong(num); //tengo q mandarle el numero al servidor siempre (aunq sea 0) para q cierre la conexion

                //solo leo la respueta si el numero no es 0
                if(num == 0) {
                    System.out.println("El cliente ha mandado 0");
                    break; //esto hace q sude del resto del programa y salte hasta fuera del blucle
                }

                //recibo la respuesta del servidor e la imprimo en pantalla
                resultado = dis.readUTF();

                if (resultado.equals("igual")) {
                    System.out.println("\n\t¡¡HAS GANADO!!\n");
                } else {
                    System.out.println("El servidor dice que el número es " + resultado);
                }

            } while (num != 0); //la unica manera de salir del bucle es que el num sea 0

            //cierro el socket de conexion con el servidor cnd salgo del bucle
            System.out.println("Cliente cerrado");
            sc.close();
            dos.close();
            dis.close();
            sckt.close();
        } catch (IOException e) {
            System.out.println("(cliente) Se ha producido la excepción: " + e);
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


