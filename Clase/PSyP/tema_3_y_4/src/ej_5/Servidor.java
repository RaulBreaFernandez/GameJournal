package ej_5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Servidor {
    static ServerSocket ss;
    static Socket sckt;
    static DataInputStream dis;
    static DataOutputStream dos;

    public static void main(String args[]) {
        long num1;
        long num2;

        try {
            System.out.println("LocalHost = " + InetAddress.getLocalHost().toString());
        } catch (UnknownHostException e) {
            System.err.println("No puedo saber la dirección IP local: " + e);
        }

        try {
            ss = new ServerSocket(1234);

            while (true) {
                sckt = ss.accept();
                dis = new DataInputStream(sckt.getInputStream());
                dos = new DataOutputStream(sckt.getOutputStream());

                int puerto = sckt.getPort();
                InetAddress direcc = sckt.getInetAddress();

                num1 = dis.readLong();
                num2 = dis.readLong();

                if (num1 == 0) {
                    System.out.println("Cliente ha enviado un 0. Cerrando el servidor.");
                    break;
                }

                if (num1 > 0 && num2 > 0) {
                    // Calcular el MCD usando el algoritmo de Euclides
                    long resultado = calcularMCD(num1, num2);

                    // Enviar el resultado al cliente
                    dos.writeLong(resultado);
                    System.out.println("Cliente = " + direcc + ":" + puerto + "\tNum1 = " + num1 + "\tNum2 = " + num2
                            + "\tResultado = " + resultado);
                }

                dis.close();
                dos.close();
                sckt.close();
            }
            ss.close();
        } catch (IOException e) {
            System.out.println("(servidor) Se ha producido el error: " + e);
        }
    }
    public static long calcularMCD(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

