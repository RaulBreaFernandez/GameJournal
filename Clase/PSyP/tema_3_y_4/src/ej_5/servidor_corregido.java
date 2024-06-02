package ej_5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class servidor_corregido {
    static ServerSocket ss;
    static Socket conexion;
    static DataInputStream entrada;
    static DataOutputStream salida;
    static int num1=1, num2;

    public static void main(String args[]) {
        try {
            System.out.println("LocalHost -> " + InetAddress.getLocalHost());
        } catch (UnknownHostException uhe) {
            System.err.println("Error en el host: " + uhe);
        }

        try {
            ss = new ServerSocket(1234);

            try {
                conexion = ss.accept();
                entrada = new DataInputStream(conexion.getInputStream());
                salida = new DataOutputStream(conexion.getOutputStream());

                while (num1 != 0) {
                    num1 = entrada.readInt();
                    num2 = entrada.readInt();

                    if (num1 == 0) {
                        System.out.println("El cliente quiso cerrar");
                        break;
                    }

                    int resultado = calcularmcd(num1, num2);
                    if (num2 == 0) {
                        resultado = -33;
                    }

                    salida.writeInt(resultado);
                    System.out.println("Num1 = " + num1 + "\tNum2 = " + num2 + "\tResultado = " + resultado);
                }
                entrada.close();
                salida.close();
                conexion.close();
            } catch (IOException ec) {
                System.out.println("Error de conexion: " + ec);
            }
            ss.close();
        } catch (IOException e) {
            System.out.println("(servidor) Error en el servidor: " + e);
        }

    }

    private static int calcularmcd(int num1, int num2) {
        while (num2 != 0) {
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }

        return num1;
    }
}
