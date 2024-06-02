package ej_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class servidor_corregido {
    static ServerSocket ss;
    static Socket conexion;
    static DataInputStream entrada;
    static DataOutputStream salida;
    static int numCliente=1;
    static Random random;
    static int numRan;

    public static void main(String args[]) {
        try{
            System.out.println("LocalHost -> " + InetAddress.getLocalHost());
        }catch (UnknownHostException uhe) {
            System.err.println("Error en el Host: " + uhe);
        }

        random = new Random();
        numRan = random.nextInt(1000) + 1;
        System.out.println("Número aleatorio generado: " + numRan);

        try{
            ss = new ServerSocket(1234);

            while(true) {
                try {
                    conexion = ss.accept();
                    entrada = new DataInputStream(conexion.getInputStream());
                    salida = new DataOutputStream(conexion.getOutputStream());

                    while (numCliente != 0) {
                        numCliente = entrada.readInt();
                        System.out.println("Cliente -> " + numCliente);

                        // Si el cliente envía cero o adivina correctamente, cerramos la conexión.
                        if (numCliente == 0) {
                            System.out.println("Cerrando la conexión con el cliente.");
                            break;
                        } else if (numCliente == numRan) {
                            salida.writeUTF("igual");
                            System.out.println("Número adivinado por el cliente. Cerrando conexión.");
                            //como ha acertado genero un nuevo número para el cliente
                            numRan = random.nextInt(1000) + 1;
                            System.out.println("\nNúmero aleatorio generado: " + numRan);
                        } else if (numCliente < numRan) {
                            salida.writeUTF("mayor"); //envio la respuesta del servidor
                        } else {
                            salida.writeUTF("menor");
                        }
                    }

                    entrada.close();
                    salida.close();
                    conexion.close();
                } catch (IOException ec) {
                    System.out.println("Error de conexion: " + ec);
                }
            }
        }catch (IOException e) {
            System.out.println("(servidor) Error de servidor: " + e);
        }
    }
}
