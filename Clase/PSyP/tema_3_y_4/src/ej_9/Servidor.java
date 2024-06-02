package ej_9;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Servidor {
    static ServerSocket ss;
    static Socket conexion;
    static DataInputStream entrada;
    static DataOutputStream salida;
    static String palabra="hola"; //es la variable que guarda lo que hace el cliente
    static String respuesta; //esto es la respuesta del servidor al cliente

    public static void main(String ags[]) {
        Scanner sc = new Scanner(System.in);

        try{
            System.out.println("LocalHost -> " + InetAddress.getLocalHost());
        }catch (UnknownHostException uhe) {
            System.err.println("Error de host -> " + uhe);
        }

        System.out.println("\nEl cliente abrió una conexión");
        try{
            ss = new ServerSocket(1234);

            while(true) {
                try{
                    conexion = ss.accept();
                    entrada = new DataInputStream(conexion.getInputStream());
                    salida = new DataOutputStream(conexion.getOutputStream());

                    while(!palabra.equalsIgnoreCase("adios")) {
                        //leo lo que manda el cliente
                        palabra = entrada.readUTF();

                        if (palabra.equalsIgnoreCase("adios")){
                            System.out.println("El cliente cerro la conexión");
                            palabra = "hola";
                            entrada.close();
                            salida.close();
                            conexion.close();
                            break;
                        }

                        System.out.println("Cliente -> " + palabra);

                        //hacer para q yo tenga q escribir el mensaje q se envia desde el servidor
                        System.out.print("Escriba la respuesta del servidor: ");
                        respuesta = sc.nextLine();


                        /* switch (palabra){
                            case "hola":
                                respuesta = "Buenos dias";
                                break;
                            case "necesito":
                                respuesta = "Dime lo que necesitas";
                                break;
                            case "cosa":
                                respuesta = "que cosita es";
                                break;
                            default:
                                respuesta = "me has dejado sin palabras";
                                break;
                        } */

                        //envio la respueesta del servidor
                        salida.writeUTF(respuesta);
                    }

                }catch (IOException err) {
                    System.err.println("(error conexión) Error: " + err);
                }
            }
        }catch (IOException e) {
            System.out.println("(Servidor) Error: " + e);
        }
    }
}
