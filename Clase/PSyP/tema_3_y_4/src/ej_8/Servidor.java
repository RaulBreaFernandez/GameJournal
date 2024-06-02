//Realizar un proyecto cliente-servidor en el que el
// servidor haga eco de lo que dice el cliente hasta
//que el cliente le envíe adiós en cuyo cayo se cerrará
// el cliente y el servidor seguirá activo.

package ej_8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Servidor {
    static ServerSocket ss;
    static Socket conexion;
    static DataInputStream entrada;
    static DataOutputStream salida;
    static String palabra="hola";

    public static void main(String args[]) {
        try{
            System.out.println("LocalHost -> " + InetAddress.getLocalHost().toString());
        } catch (UnknownHostException sce) {
            System.err.println("No se pudo saber la direccion IP local: " + sce);
        }

        try{
            ss = new ServerSocket(1234);

            while (true){
                try{
                    conexion = ss.accept();
                    entrada = new DataInputStream(conexion.getInputStream());
                    salida = new DataOutputStream(conexion.getOutputStream());

                    while (!palabra.equalsIgnoreCase("adios")) {
                        //leo lo a manda el cliente
                        palabra = entrada.readUTF();

                        //si la palabra q envia el cliente es adios , cierro la conexion con el cliente
                        if (palabra.equalsIgnoreCase("adios")) {
                            palabra = "hola";
                            entrada.close();
                            salida.close();
                            conexion.close();
                            break;
                        }

                        //imprimo lo q me manda el cliente
                        System.out.println("Cliente -> " + palabra);

                        //como quiero q diga lo mismo, envio la propia variable que recoge lo q envia el cliente
                        salida.writeUTF(palabra);
                    }
                }catch (IOException err) {
                    System.out.println("(error conexion)Error -> " + err);
                }
            }
        }catch (IOException e){
            System.out.println("(Servidor) Error: " + e);
        }
    }
}
