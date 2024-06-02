package ej_10;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Servidor {
    static ServerSocket ss;
    static Socket conexion;
    static DataOutputStream salida;
    static String linea="Prueba";

    public static void main(String args[]) {
        try {
            System.out.println("LocalHost -> " + InetAddress.getLocalHost());
        }catch (UnknownHostException uhe) {
            System.err.println("Error de servidor: " + uhe);
        }

        try{
            ss = new ServerSocket(1234);

            while (true) {
                try{
                    conexion = ss.accept();
                    salida = new DataOutputStream(conexion.getOutputStream());

                    //determino q archivo es e inicio un BufferedReader
                    File archivo = new File("D:\\DAM 2\\programacion de servicios\\tema_3_y_4\\src\\ej_10\\fichero.txt");
                    BufferedReader br = new BufferedReader(new FileReader(archivo));

                    while (linea!=null){
                        linea = br.readLine();

                        //digo q mientras q linea el br.readLine y q siga mandado mientras no sea null
                        if(linea == null){
                            salida.writeInt(0);
                            System.out.println("\nNo quedan lineas en el archivo");
                            salida.close();
                            conexion.close();
                            break;
                        }

                        System.out.println(linea);
                        salida.writeInt(1);
                        salida.writeUTF(linea);

                    }
                }catch (IOException err){
                    System.out.println("(error conexion) Error: " + err);
                }

            }
        }catch (IOException e){
            System.out.println("(Servidor) Error: " + e);
        }

    }
}
