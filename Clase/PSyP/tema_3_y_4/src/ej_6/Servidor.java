//Realizar un proyecto cliente-servidor en el que el cliente tenga que averiguar un número aleatorio
//generado por el servidor entre 1 y 1000; el servidor informará al cliente si el número generado es
//mayor o menor que el introducido por el cliente. El cliente finalizará cuando introduzca un cero. El
//servidor seguirá activo.

package ej_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Servidor {
    static ServerSocket ss;
    static Socket sckt;
    static DataInputStream dis;
    static DataOutputStream dos;
    static Random random;
    static int numRan;

    public static void main(String args[]) {
        try {
            System.out.println("LocalHost = " + InetAddress.getLocalHost().toString());
        } catch (UnknownHostException sce) {
            System.err.println("No puedo saber la dirección IP local: " + sce);
        }

        random = new Random();
        numRan = random.nextInt(1000) + 1;
        System.out.println("Número aleatorio generado: " + numRan);

        try {
            ss = new ServerSocket(1234);

            while (true) {
                sckt = ss.accept();
                dis = new DataInputStream(sckt.getInputStream());
                dos = new DataOutputStream(sckt.getOutputStream());

                while (true) {
                    long respuesta = dis.readLong();
                    System.out.println("Cliente -> " + respuesta);

                    // Si el cliente envía cero o adivina correctamente, cerramos la conexión.
                    if (respuesta == 0) {
                        System.out.println("Cerrando la conexión con el cliente.");
                        break;
                    } else if (respuesta == numRan) {
                        dos.writeUTF("igual");
                        System.out.println("Número adivinado por el cliente. Cerrando conexión.");
                        //como ha acertado genero un nuevo número para el cliente
                        numRan = random.nextInt(1000) + 1;
                        System.out.println("\nNúmero aleatorio generado: " + numRan);
                    } else if (respuesta < numRan) {
                        dos.writeUTF("mayor"); //envio la respuesta del servidor
                    } else {
                        dos.writeUTF("menor");
                    }
                }

                dis.close();
                dos.close();
                sckt.close();
            }
        } catch (IOException e) {
            System.out.println("(servidor) Se ha producido un error: " + e);
        }
    }
}

