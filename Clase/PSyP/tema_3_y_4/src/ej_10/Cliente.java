package ej_10;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente {
    static int numero_servidor=1;

    public static void main(String args[]) {
        InetAddress direcc = null;
        Socket conexion;

        try{
            try{
                direcc = InetAddress.getLocalHost();
            }catch (UnknownError ue){
                System.err.println("Error host: " + ue);
            }

            conexion = new Socket(direcc, 1234);
            DataInputStream entrada = new DataInputStream(conexion.getInputStream());

            while(numero_servidor != 0) {
                numero_servidor = entrada.readInt();
                if (numero_servidor==0){
                    System.out.println("\nNo quedan lineas");
                    break;
                }
                String lo_que_manda_el_servidor = entrada.readUTF();                System.out.println(lo_que_manda_el_servidor);
            }

            entrada.close();
            conexion.close();

        }catch (IOException e){
            System.out.println("(cliente) Error: " + e);
        }

    }
}
